package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.exception.BizException;
import com.company.scma.common.po.*;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.*;
import com.company.scma.service.bizservice.PartnershipBizService;
import com.company.scma.service.bizservice.UserBizService;
import com.company.scma.service.mapperservice.*;
import com.company.scma.service.validateservice.PartnershipValidateService;
import com.company.scma.task.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
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
    @Autowired
    private MemberService memberService;
    @Autowired
    private ScheduledTask scheduledTask;
    @Autowired
    private DictionaryService dictionaryService;


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
    public Result downloadPartnershipData(GetPartnershipDTO getPartnershipDTO) {
        //参数校验
        Result result = partnershipValidateService.validateGetPartnershipDTO(getPartnershipDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TPartnership> tPartnershipIPage = partnershipService.getPartnershipByCondition(getPartnershipDTO);
        if(ObjectUtil.isEmpty(tPartnershipIPage)){
            return Result.getResult(ResultEnum.NO_EXCEL_DATA);
        }
        //查询所有单位性质
        String partnershipTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_TYPE);
        List<PartnershipTypeVO> allPartnershipType = JSON.parseArray(partnershipTypeStr, PartnershipTypeVO.class);
        //查询所有活动类型
        String partnershipProjectTypeStr
                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_PROJECT_TYPE);
        List<PartnershipProjectTypeVO> allPartnershipProjectType = JSON.parseArray(partnershipProjectTypeStr, PartnershipProjectTypeVO.class);
        //查询所有股票类型
        String stockTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.STOCK_TYPE);
        List<StockTypeVO> allStockType = JSON.parseArray(stockTypeStr, StockTypeVO.class);
        //查询所有负责人职务类型
        List<TDictionary> tDictionaryListByDirectorPosition
                = dictionaryService.selectTDictionaryByDicType(Constant.DicType.DIRECTOR_POSITION_TYPE);
        List<PositionVO> allDirectorPositionList = GenerateUtil.getDicDataVOList(PositionVO.class, tDictionaryListByDirectorPosition);
        //查询所有联系人职务类型
        List<TDictionary> tDictionaryListByContactPosition
                = dictionaryService.selectTDictionaryByDicType(Constant.DicType.CONTACT_POSITION_TYPE);
        List<PositionVO> allContactPositionList = GenerateUtil.getDicDataVOList(PositionVO.class, tDictionaryListByContactPosition);
        //数据封装
        List<PartnershipExcelVO> partnershipExcelVOList = 
                GenerateUtil.getPartnershipExcelVOList(tPartnershipIPage, allPartnershipType, allPartnershipProjectType, allStockType, allDirectorPositionList, allContactPositionList);
        //输出流
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        EasyExcel.write(bao,PartnershipExcelVO.class).sheet(Constant.Common.SHEET_NAME).doWrite(partnershipExcelVOList);
        //返回
        return Result.success(bao.toByteArray());
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
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        PartnershipDetailVO partnershipDetailVO = GenerateUtil.getPartnershipDetailVO(tPartnership);
        //查询所有企业性质
        String partnershipTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_TYPE);
        List<PartnershipTypeVO> allPartnershipType = JSON.parseArray(partnershipTypeStr, PartnershipTypeVO.class);
        //获取当前企业性质
        PartnershipTypeVO myPartnershipType = GenerateUtil.getPartnershipTypeVO(allPartnershipType, tPartnership.getPartnershipType());
        //查询所有活动类型
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
        //查询股票类型
        Integer isListed = tPartnership.getIsListed();
        if(Constant.Judge.YES.equals(isListed)){
            //查询所有股票类型
            String stockTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.STOCK_TYPE);
            List<StockTypeVO> allStockType = JSON.parseArray(stockTypeStr, StockTypeVO.class);
            //获取当前股票类型
            Integer stockTypeId = tPartnership.getStockTypeId();
            if(ObjectUtil.isNotEmpty(stockTypeId)){
                StockTypeVO myStockType = GenerateUtil.getStockTypeVO(allStockType, stockTypeId);
                partnershipDetailVO.setMyStockType(myStockType);
                partnershipDetailVO.setAllStockType(allStockType);
            }
        }
        //查询当前活动
        Integer operationId = tPartnership.getOperationId();
        if(ObjectUtil.isNotEmpty(operationId)){
            TOperation tOperation = operationService.getTOperationById(operationId);
            OperationListRowVO myOperation = GenerateUtil.getOperationListRowVO(tOperation);
            //查询当前所有合法活动
            List<TOperation> allValidOperation = operationService.getAllValidOperation();
            List<OperationListRowVO> allOperation = new ArrayList<>();
            if(ObjectUtil.isNotEmpty(allValidOperation)){
                allOperation = allValidOperation.stream().map(GenerateUtil::getOperationListRowVO).collect(Collectors.toList());
            }
            partnershipDetailVO.setMyOperation(myOperation);
            partnershipDetailVO.setAllOperation(allOperation);
        }
        //查询管理员账号
        Integer managerUserid = tPartnership.getManagerUserid();
        if(ObjectUtil.isNotEmpty(managerUserid)){
            TUser manager = userService.getUserByUserid(managerUserid);
            //查询所有子账号,包括已经失效的子账号
            List<TUser> subAccountList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, partnershipId, null);
            if(ObjectUtil.isNotEmpty(manager)){
                partnershipDetailVO.setUsername(manager.getUsername());
            }
            if(ObjectUtil.isNotEmpty(subAccountList)){
                int size = subAccountList.size();
                if(ObjectUtil.isNotEmpty(manager)){
                    //如果有管理员，去除管理员数量
                    size = size - 1;
                }
                partnershipDetailVO.setSubAccountNum(size);
            }
        }
        //查询所有负责人职务类型
//        String directorPositionTypeStr
//                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.DIRECTOR_POSITION_TYPE);
//        List<PositionVO> allDirectorPositionList = JSON.parseArray(directorPositionTypeStr, PositionVO.class);
        List<TDictionary> tDictionaryListByDirectorPosition 
                = dictionaryService.selectTDictionaryByDicType(Constant.DicType.DIRECTOR_POSITION_TYPE);
        List<PositionVO> allDirectorPositionList = GenerateUtil.getDicDataVOList(PositionVO.class, tDictionaryListByDirectorPosition);
        //查询所有联系人职务类型
//        String contactPositionTypeStr
//                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.CONTACT_POSITION_TYPE);
//        List<PositionVO> allContactPositionList = JSON.parseArray(contactPositionTypeStr, PositionVO.class);
        List<TDictionary> tDictionaryListByContactPosition
                = dictionaryService.selectTDictionaryByDicType(Constant.DicType.CONTACT_POSITION_TYPE);
        List<PositionVO> allContactPositionList = GenerateUtil.getDicDataVOList(PositionVO.class, tDictionaryListByContactPosition);
        //获取联系人职务类型
        PositionVO contactPosition = GenerateUtil.getPositionVO(allContactPositionList, tPartnership.getContactPositionId());
        //获取负责人职务类型
        PositionVO directorPosition = GenerateUtil.getPositionVO(allDirectorPositionList, tPartnership.getDirectorPositionId());
        //封装
        partnershipDetailVO.setMyPartnershipType(myPartnershipType);
        partnershipDetailVO.setAllPartnershipType(allPartnershipType);
        partnershipDetailVO.setMyProjectType(myPartnershipProjectType);
        partnershipDetailVO.setAllProjectType(allPartnershipProjectType);
        partnershipDetailVO.setAllDirectorPosition(allDirectorPositionList);
        partnershipDetailVO.setAllContactPosition(allContactPositionList);
        partnershipDetailVO.setDirectorPosition(directorPosition);
        partnershipDetailVO.setContactPosition(contactPosition);
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
        Object data = result.getData();
        Integer operationStatus = null;
        if(ObjectUtil.isNotEmpty(data)){
            operationStatus = (Integer) data;
        }
        TPartnership tPartnership = GenerateUtil.getTPartnership(createPartnershipDTO,operationStatus);
        //生成新的字典数据
        String newContactPosition = createPartnershipDTO.getNewContactPosition();
        String newDirectorPosition = createPartnershipDTO.getNewDirectorPosition();
        if(ObjectUtil.isNotEmpty(newDirectorPosition)){
            Integer directorPositionId 
                    = dictionaryService.insertByDicType(newDirectorPosition, Constant.DicType.DIRECTOR_POSITION_TYPE);
            tPartnership.setDirectorPositionId(directorPositionId);
        }
        if(ObjectUtil.isNotEmpty(newContactPosition)){
            Integer contactPositionId
                    = dictionaryService.insertByDicType(newContactPosition, Constant.DicType.CONTACT_POSITION_TYPE);
            tPartnership.setContactPositionId(contactPositionId);
        }
        //入库
        partnershipService.save(tPartnership);
        //生成管理员账号
        String managerUsername = createPartnershipDTO.getManagerUsername();
        String managerPassword = createPartnershipDTO.getManagerPassword();
        Integer partnershipId = tPartnership.getPartnershipId();
        if(ObjectUtil.isNotEmpty(managerUsername) && ObjectUtil.isNotEmpty(managerPassword)){
            String partnershipName = createPartnershipDTO.getPartnershipName();

            Result managerResult = userBizService.
                    createUserByPartnership(managerUsername, partnershipName ,managerPassword,partnershipId, Constant.SubAccountType.MANAGER);
            if(!Result.isSuccess(managerResult)){
                throw new BizException(managerResult.getCode(),managerResult.getMsg());
            }
            //管理员授权
            TUser manager = (TUser) managerResult.getData();
            manager.setRoleId(Constant.CommonRoleId.PARTNERSHIP_MANAGER);
            //管理员状态设置
            Integer accountStatus = operationStatus.equals(Constant.OperationStatus.NORMAL) ? Constant.Judge.YES : Constant.Judge.NO;
            manager.setStatus(accountStatus);
            //管理员账号入库
            userService.save(manager);
            //合作企业更新
            tPartnership.setManagerUserid(manager.getUserid());
            partnershipService.updateById(tPartnership);
            Integer subAccountNum = createPartnershipDTO.getSubAccountNum();
            if(ObjectUtil.isNotEmpty(subAccountNum)){
                //生成子账号
                List<TUser> subAccountList = new ArrayList<>();
                if(ObjectUtil.isNotEmpty(subAccountNum) && 0 != subAccountNum){
                    for(int i = 1;i<=subAccountNum;i++){
                        String subAccountUsername = managerUsername + "-" + i;
                        String subAccountName = partnershipName + "-" + i;
                        Result subAccountResult = userBizService.
                                createUserByPartnership(subAccountUsername, subAccountName, managerPassword, partnershipId,Constant.SubAccountType.SUB_ACCOUNT);
                        if(!Result.isSuccess(subAccountResult)){
                            throw new BizException(subAccountResult.getCode(),subAccountResult.getMsg());
                        }
                        TUser subAccount = (TUser) subAccountResult.getData();
                        //子账号授权
                        subAccount.setRoleId(Constant.CommonRoleId.PARTNERSHIP_USER);
                        //子账号状态设置
                        subAccount.setStatus(accountStatus);
                        subAccountList.add(subAccount);
                    }
                }
                //子账号入库
                userService.saveBatch(subAccountList);
            }
        }
        //生成关系实体类
        if(ObjectUtil.isNotEmpty(data)){
            TOperationOtmPartnership tOperationOtmPartnership = GenerateUtil.getTOperationOtmPartnership(createPartnershipDTO.getOperationId(), partnershipId);
            //入库
            operationOtmPartnershipService.save(tOperationOtmPartnership);
        }
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
        Object data = result.getData();
        Integer operationStatus = null;
        if(ObjectUtil.isNotEmpty(data)){
            operationStatus = (Integer) data;
        }
        TPartnership inputTPartnership = GenerateUtil.getTPartnership(editPartnershipDTO,operationStatus);
        //生成新的字典数据
        String newContactPosition = editPartnershipDTO.getNewContactPosition();
        String newDirectorPosition = editPartnershipDTO.getNewDirectorPosition();
        if(ObjectUtil.isNotEmpty(newDirectorPosition)){
            Integer directorPositionId
                    = dictionaryService.insertByDicType(newDirectorPosition, Constant.DicType.DIRECTOR_POSITION_TYPE);
            inputTPartnership.setDirectorPositionId(directorPositionId);
        }
        if(ObjectUtil.isNotEmpty(newContactPosition)){
            Integer contactPositionId
                    = dictionaryService.insertByDicType(newContactPosition, Constant.DicType.CONTACT_POSITION_TYPE);
            inputTPartnership.setContactPositionId(contactPositionId);
        }
        partnershipService.saveOrUpdate(inputTPartnership);
        //设置子账号状态
        Integer inputSubAccountNum = editPartnershipDTO.getSubAccountNum();
        Integer partnershipId = editPartnershipDTO.getPartnershipId();
        TPartnership selectTPartnership = partnershipService.getTPartnershipById(partnershipId);
        if(ObjectUtil.isNotEmpty(inputSubAccountNum)){
            Integer accountStatus = operationStatus.equals(Constant.OperationStatus.NORMAL) ? Constant.Judge.YES : Constant.Judge.NO;
            //查询当前管理员账号
            Integer managerUserid = selectTPartnership.getManagerUserid();
            TUser manager = userService.getUserByUserid(managerUserid);
            //查询当前子账号数量,包括已经失效的账号
            List<TUser> tUserList = userService.getUserByTypeAndBuildId(Constant.UserType.SUB_ACCOUNT_USER, partnershipId,null);
            int currentSubAccountNum = tUserList.size();
            //生成子账号
            if(inputSubAccountNum > currentSubAccountNum){
                String partnershipName = editPartnershipDTO.getPartnershipName();
                List<TUser> subAccountList = new ArrayList<>();
                String subAccountPassword = null;
                String managerUsername = null;
                if(ObjectUtil.isNotEmpty(manager)){
                    //不为空时查询出来的子账号数量会加上管理员本身
                    subAccountPassword = manager.getPassword();
                    currentSubAccountNum = currentSubAccountNum - 1;
                    managerUsername = manager.getUsername();
                }else{
                    subAccountPassword = Constant.Common.DEFAULT_SUB_ACCOUNT_PASSWORD;
                    managerUsername = partnershipName;
                }
                for (int i = currentSubAccountNum + 1;i<=inputSubAccountNum;i++){
                    String subAccountUsername = managerUsername + "-" + i;
                    String subAccountName = partnershipName + i;
                    Result subAccountResult = userBizService.
                            createUserByPartnership(subAccountUsername, subAccountName, subAccountPassword, partnershipId,Constant.SubAccountType.SUB_ACCOUNT);
                    if(!Result.isSuccess(subAccountResult)){
                        throw new BizException(subAccountResult.getCode(),subAccountResult.getMsg());
                    }
                    TUser subAccount = (TUser) subAccountResult.getData();
                    //生成方法中会将密码加密，这里从数据库中取出的密码本身就已经加密过，不用再次加密，这里重新设置一下
                    subAccount.setPassword(subAccountPassword);
                    //子账号授权
                    subAccount.setRoleId(Constant.CommonRoleId.ADMIN_USER);
                    //子账号状态设置
                    subAccount.setStatus(accountStatus);
                    subAccountList.add(subAccount);
                }
                //子账号入库
                userService.saveBatch(subAccountList);
            }
        }
        //删除绑定关系
        Integer operationId = editPartnershipDTO.getOperationId();
        if(ObjectUtil.isNotEmpty(operationId)){
            if(ObjectUtil.isEmpty(operationId) || !operationId.equals(selectTPartnership.getOperationId())){
                operationOtmPartnershipService.deleteByPartnershipIdAndOperationId(partnershipId,selectTPartnership.getOperationId());
                //生成新的关联关系
                TOperationOtmPartnership tOperationOtmPartnership = GenerateUtil.getTOperationOtmPartnership(operationId, partnershipId);
                //插入绑定关系
                operationOtmPartnershipService.save(tOperationOtmPartnership);
            }
        }

        //处理会员所属企业名称
        List<TMember> tMemberList = memberService.getMemberByOwnerPartnershipId(partnershipId);
        if(ObjectUtil.isNotEmpty(tMemberList)){
            tMemberList.stream().forEach(tMember -> {
                tMember.setOwnerPartnershipName(editPartnershipDTO.getPartnershipName());
            });
            memberService.saveOrUpdateBatch(tMemberList);
        }
        return Result.success();
    }

    @Override
    public Result deletePartnership(Integer partnershipId) {
        //参数校验
        Result result = partnershipValidateService.validateDeletePartnershipId(partnershipId);
        if(!Result.isSuccess(result)){
            return result;
        }
        //删除合作企业
        partnershipService.deleteByPartnershipId(partnershipId);
        //设置对应的用户和会员
        List permissionIdList = new ArrayList<>();
        permissionIdList.add(partnershipId);
        String releaseWay = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_WAY);
        scheduledTask.operateDeletedPartnership(permissionIdList,releaseWay);
//        //删除合作企业不删除对应关系，这样定时任务在遍历时可以找到这个合作企业
//        operationOtmPartnershipService.deleteByPartnershipId(partnershipId);
        //返回
        return Result.success();
    }
}
