package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.po.TOperation;
import com.company.scma.common.po.TPartnership;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.OperationService;
import com.company.scma.service.mapperservice.PartnershipService;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.PartnershipValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PartnershipValidateServiceImpl implements PartnershipValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private PartnershipService partnershipService;
    @Override
    public Result validateCreatePartnershipDTO(CreatePartnershipDTO createPartnershipDTO) {
        if(ObjectUtil.isEmpty(createPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(createPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        //校验partnershipName名字是否重复
        String partnershipName = createPartnershipDTO.getPartnershipName();
        List<TPartnership> tPartnershipList = partnershipService.getTPartnershipByName(partnershipName);
        if(ObjectUtil.isNotEmpty(tPartnershipList)){
            return Result.getResult(ResultEnum.EXIST_PARTNERSHIP_NAME);
        }

        //校验绑定的活动状态
        Integer operationId = createPartnershipDTO.getOperationId();
        TOperation tOperationById = operationService.getTOperationById(operationId);
        if(Constant.OperationStatus.FINISH.equals(tOperationById.getStatus())){
            return Result.getResult(ResultEnum.ERROR_OPERATION_STATUS);
        }
        return Result.success(tOperationById.getStatus());
    }

    @Override
    public Result validateGetPartnershipDTO(GetPartnershipDTO getPartnershipDTO) {
        if(ObjectUtil.isEmpty(getPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(getPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateEditPartnershipDTO(EditPartnershipDTO editPartnershipDTO) {
        if(ObjectUtil.isEmpty(editPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(editPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        //校验绑定的活动状态
        Integer operationId = editPartnershipDTO.getOperationId();
        TOperation tOperationById = operationService.getTOperationById(operationId);
        if(Constant.OperationStatus.FINISH.equals(tOperationById.getStatus())){
            return Result.getResult(ResultEnum.ERROR_OPERATION_STATUS);
        }
        return Result.success(tOperationById.getStatus());
    }
}
