package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateArticleDTO;
import com.company.scma.common.vo.Result;

import java.util.List;

public interface ArticleBizService {
    public Result createArticle(CreateArticleDTO createArticleDTO);

    public Result deleteArticle(List<Integer> articleIdList);
}
