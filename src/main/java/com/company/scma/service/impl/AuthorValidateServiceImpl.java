package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateAuthorDTO;
import com.company.scma.common.dto.EditAuthorDTO;
import com.company.scma.common.dto.GetAuthorDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.validateservice.AuthorValidateService;
import com.company.scma.service.validateservice.CommonValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorValidateServiceImpl implements AuthorValidateService {
    @Autowired
    private CommonValidateService commonValidateService;

    @Override
    public Result validateGetAuthorDTO(GetAuthorDTO getAuthorDTO) {
        if(ObjectUtil.isEmpty(getAuthorDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(getAuthorDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();    }

    @Override
    public Result validateCreateAuthorDTO(CreateAuthorDTO createAuthorDTO) {
        if(ObjectUtil.isEmpty(createAuthorDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(createAuthorDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }

    @Override
    public Result validateEditAuthorDTO(EditAuthorDTO editAuthorDTO) {
        if(ObjectUtil.isEmpty(editAuthorDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(editAuthorDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();    }
}
