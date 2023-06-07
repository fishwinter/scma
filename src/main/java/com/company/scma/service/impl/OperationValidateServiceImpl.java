package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateOperationDTO;
import com.company.scma.common.dto.EditOperationDTO;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.OperationValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationValidateServiceImpl implements OperationValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    
    @Override
    public Result validateCreateOperationDTO(CreateOperationDTO createOperationDTO) {
        if(ObjectUtil.isEmpty(createOperationDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        if(!commonValidateService.validateAnnotation(createOperationDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateEditOperationDTO(EditOperationDTO editOperationDTO) {
        if(ObjectUtil.isEmpty(editOperationDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        if(!commonValidateService.validateAnnotation(editOperationDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateGetOperationDTO(GetOperationDTO getOperationDTO) {
        if(ObjectUtil.isEmpty(getOperationDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        if(!commonValidateService.validateAnnotation(getOperationDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }
}
