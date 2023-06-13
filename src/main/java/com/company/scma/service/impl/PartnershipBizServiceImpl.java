package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.po.TOperation;
import com.company.scma.common.po.TOperationOtmPartnership;
import com.company.scma.common.po.TPartnership;
import com.company.scma.common.po.TUser;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.*;
import com.company.scma.service.bizservice.PartnershipBizService;
import com.company.scma.service.bizservice.UserBizService;
import com.company.scma.service.mapperservice.*;
import com.company.scma.service.validateservice.PartnershipValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnershipBizServiceImpl implements PartnershipBizService {
    @Autowired
    private PartnershipValidateService partnershipValidateService;
    @Autowired
    private UserBizService userBizService;
    @Autowired
    private PartnershipService partnershipService;
    @Autowired
    private UserService userService;
    @Autowired
    private OperationOtmPartnershipService operationOtmPartnershipService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private OperationService operationService;

    @Override
    public Result getPartnership(GetPartnershipDTO getPartnershipDTO) {
        //参数校验
        Result result = partnershipValidateService.validateGetPartnershipDTO(getPartnershipDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TPartnership> tPartnershipIPage = partnershipService.getPartnershipByCondition(getPartnershipDTO);
        //数据封装
        PartnershipListVO partnershipListVO = GenerateUtil.getPartnershipListVO(tPartnershipIPage);
        //返回
        return Result.success(partnershipListVO);
    }

    @Override
    public Result getPartnershipDetail(Integer partnershipId) {
        //参数校验
        if(ObjectUtil.isEmpty(partnershipId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //数据查询
        TPartnership tPartnership = partnershipService.getTPartnershipById(partnershipId);
        //基础数据封装
        if(ObjectUtil.isEmpty(tPartnership)){
            return null;
        }
        PartnershipDetailVO partnershipDetailVO = GenerateUtil.getPartnershipDetailVO(tPartnership);
        //查询所有企业性质
        String partnershipTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_TYPE);
        List<PartnershipTypeVO> allPartnershipType = JSON.parseArray(partnershipTypeStr, PartnershipTypeVO.class);
        //获取当前企业性质
        PartnershipTypeVO myPartnershipType = GenerateUtil.getPartnershipTypeVO(allPartnershipType, tPartnership.getPartnershipType());
        //查询所有活动性质
        String partnershipProjectTypeStr
                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_PROJECT_TYPE);
        List<PartnershipProjectTypeVO> allPartnershipProjectType = JSON.parseArray(partnershipProjectTypeStr, PartnershipProjectTypeVO.class);
        //获取当前企业活动性质
        String projectType = tPartnership.getProjectType();
        List<PartnershipProjectTypeVO> myPartnershipProjectType = null;
        if(ObjectUtil.isNotEmpty(projectType)){
            String[] split = projectType.split(",");
            List<String> projectTypeList = Arrays.asList(split);
            myPartnershipProjectType = GenerateUtil.getPartnershipProjectTypeVO(allPartnershipProjectType, projectTypeList);
        }
        //查询当前活动
        Integer operationId = tPartnership.getOperationId();
        TOperation tOperation = operationService.getTOperationById(operationId);
        OperationListRowVO myOperation = GenerateUtil.getOperationListRowVO(tOperation);
        //查询当前所有合法活动
        List<TOperation> allValidOperation = operationService.getAllValidOperation();
        List<OperationListRowVO> allOperation = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(allValidOperation)){
            allOperation = allValidOperation.stream().map(GenerateUtil::getOperationListRowVO).collect(Collectors.toList());
        }
        //封装
        partnershipDetailVO.setMyPartnershipType(myPartnershipType);
        partnershipDetailVO.setAllPartnershipType(allPartnershipType);
        partnershipDetailVO.setMyProjectType(myPartnershipProjectType);
        partnershipDetailVO.setAllProjectType(allPartnershipProjectType);
        partnershipDetailVO.setMyOperation(myOperation);
        partnershipDetailVO.setAllOperation(allOperation);
        //返回
        return Result.success(partnershipDetailVO);
    }

    @Override
    @Transactional
    public Result createPartnership(CreatePartnershipDTO createPartnershipDTO) {
        //参数校验
        Result result = partnershipValidateService.validateCreatePartnershipDTO(createPartnershipDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成合作企业实体类
        Integer operationStatus = (Integer) result.getData();
        TPartnership tPartnership = GenerateUtil.getTPartnership(createPartnershipDTO,operationStatus);
        //入库
        partnershipService.save(tPartnership);
        //生成管理员账号
        String managerUsername = createPartnershipDTO.getManagerUsername();
        String managerPassword = createPartnershipDTO.getManagerPassword();
        Integer partnershipId = tPartnership.getPartnershipId();

        Result managerResult = userBizService.
                createUserByPartnership(managerUsername, managerPassword,partnershipId, Constant.SubAccountType.MANAGER);
        if(!Result.isSuccess(managerResult)){
            return managerResult;
        }
        //管理员授权
        
        //管理员账号入库
        TUser manager = (TUser) managerResult.getData();
        userService.save(manager);
        //合作企业更新
        tPartnership.setManagerUserid(manager.getUserid());
        partnershipService.updateById(tPartnership);
        //生成子账号
        Integer subAccountNum = createPartnershipDTO.getSubAccountNum();
        List<TUser> subAccountList = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(subAccountNum) && 0 != subAccountNum){
            String partnershipName = createPartnershipDTO.getPartnershipName();
            for(int i = 1;i<=subAccountNum;i++){
                String subAccountUsername = partnershipName + "-" + i;
                Result subAccountResult = userBizService.
                        createUserByPartnership(subAccountUsername, managerPassword, partnershipId,Constant.SubAccountType.SUB_ACCOUNT);
                if(!Result.isSuccess(subAccountResult)){
                    return subAccountResult;
                }
                TUser subAccount = (TUser) subAccountResult.getData();
                //子账号授权
                
                subAccountList.add(subAccount);
            }
        }
        //子账号入库
        userService.saveBatch(subAccountList);
        //生成关系实体类
        TOperationOtmPartnership tOperationOtmPartnership = GenerateUtil.getTOperationOtmPartnership(createPartnershipDTO.getOperationId(), partnershipId);
        //入库
        operationOtmPartnershipService.save(tOperationOtmPartnership);
        //返回
        return Result.success();
    }

    @Override
    public Result editPartnership(EditPartnershipDTO editPartnershipDTO) {
        //参数校验
        Result result = partnershipValidateService.validateEditPartnershipDTO(editPartnershipDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //修改合作企业
        Integer operationStatus = (Integer) result.getData();
        TPartnership inputTPartnership = GenerateUtil.getTPartnership(editPartnershipDTO,operationStatus);
        partnershipService.saveOrUpdate(inputTPartnership);
        //查询当前管理员账号
        Integer partnershipId = editPartnershipDTO.getPartnershipId();
        TPartnership selectTPartnership = partnershipService.getTPartnershipById(partnershipId);
        Integer managerUserid = selectTPartnership.getManagerUserid();
        TUser manager = userService.getUserByUserid(managerUserid);
        //查询当前子账号数量
        List<TUser> tUserList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, partnershipId);
        int currentSubAccountNum = tUserList.size();
        //生成子账号
        Integer inputSubAccountNum = editPartnershipDTO.getSubAccountNum();
        if(inputSubAccountNum > currentSubAccountNum){
            String partnershipName = editPartnershipDTO.getPartnershipName();
            List<TUser> subAccountList = new ArrayList<>();
            for (int i = currentSubAccountNum;i<=inputSubAccountNum;i++){
                String subAccountUsername = partnershipName + "-" + i;
                Result subAccountResult = userBizService.
                        createUserByPartnership(subAccountUsername, manager.getPassword(), partnershipId,Constant.SubAccountType.SUB_ACCOUNT);
                if(!Result.isSuccess(subAccountResult)){
                    return subAccountResult;
                }
                TUser subAccount = (TUser) subAccountResult.getData();
                //生成方法中会将密码加密，这里从数据库中取出的密码本身就已经加密过，不用再次加密，这里重新设置一下
                subAccount.setPassword(manager.getPassword());
                //子账号授权
                
                subAccountList.add(subAccount);
            }
            //子账号入库
            userService.saveBatch(subAccountList);
        }
        //删除绑定关系
        Integer operationId = editPartnershipDTO.getOperationId();
        if(ObjectUtil.isEmpty(operationId) || !operationId.equals(selectTPartnership.getOperationId())){
            operationOtmPartnershipService.deleteByPartnershipIdAndOperationId(partnershipId,selectTPartnership.getOperationId());
            //生成新的关联关系
            TOperationOtmPartnership tOperationOtmPartnership = GenerateUtil.getTOperationOtmPartnership(operationId, partnershipId);
            operationOtmPartnershipService.save(tOperationOtmPartnership);
        }
        return Result.success();
    }

    @Override
    public Result deletePartnership(Integer partnershipId) {
        //参数校验
        if(ObjectUtil.isEmpty(partnershipId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //删除合作企业
        partnershipService.deleteByPartnershipId(partnershipId);
        //删除对应关系
        operationOtmPartnershipService.deleteByPartnershipId(partnershipId);
        //返回
        return Result.success();
    }
}
