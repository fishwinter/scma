package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateArticleDTO;
import com.company.scma.common.vo.Result;

public interface ArticleValidateService {
    public Result validateCreateArticleDTO(CreateArticleDTO createArticleDTO);
}
