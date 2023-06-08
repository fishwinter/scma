package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.po.TOperationOtmPartnership;
import com.company.scma.common.po.TPartnership;
import com.company.scma.common.po.TUser;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.PartnershipBizService;
import com.company.scma.service.bizservice.UserBizService;
import com.company.scma.service.mapperservice.OperationOtmPartnershipService;
import com.company.scma.service.mapperservice.PartnershipService;
import com.company.scma.service.mapperservice.UserService;
import com.company.scma.service.validateservice.PartnershipValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Result getPartnership(GetPartnershipDTO getPartnershipDTO) {
        return null;
    }

    @Override
    public Result getPartnershipDetail(Integer partnershipId) {
        return null;
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
        TPartnership tPartnership = GenerateUtil.getTPartnership(createPartnershipDTO);
        //入库
        partnershipService.save(tPartnership);
        //生成管理员账号
        String managerUsername = createPartnershipDTO.getManagerUsername();
        String managerPassword = createPartnershipDTO.getManagerPassword();
        Integer partnershipId = tPartnership.getPartnershipId();
        Integer operationStatus = (Integer) result.getData();
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
        
        //修改合作企业
        
        //修改主账号
        
        //修改子账号
        
        //修改绑定关系
        return null;
    }

    @Override
    public Result deletePartnership(Integer partnershipId) {
        return null;
    }
}
