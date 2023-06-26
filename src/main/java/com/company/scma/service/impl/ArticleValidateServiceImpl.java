package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateArticleDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.validateservice.ArticleValidateService;
import com.company.scma.service.validateservice.CommonValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleValidateServiceImpl implements ArticleValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    
    @Override
    public Result validateCreateArticleDTO(CreateArticleDTO createArticleDTO) {
        if(ObjectUtil.isEmpty(createArticleDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(createArticleDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success();
    }
}
