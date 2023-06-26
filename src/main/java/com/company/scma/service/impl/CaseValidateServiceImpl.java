package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateCaseDTO;
import com.company.scma.common.dto.EditCaseDTO;
import com.company.scma.common.dto.GetCaseDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.validateservice.CaseValidateService;
import com.company.scma.service.validateservice.CommonValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseValidateServiceImpl implements CaseValidateService {
    @Autowired
    private CommonValidateService commonValidateService;

    @Override
    public Result validateGetCaseDTO(GetCaseDTO getCaseDTO) {
        if(ObjectUtil.isEmpty(getCaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(getCaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateCreateCaseDTO(CreateCaseDTO createCaseDTO) {
        if(ObjectUtil.isEmpty(createCaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(createCaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateEditCaseDTO(EditCaseDTO editCaseDTO) {
        if(ObjectUtil.isEmpty(editCaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(editCaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();    }
}
