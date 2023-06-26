package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TArticle;

import java.util.List;

public interface ArticleService extends IService<TArticle> {
    public List<TArticle> getTArticleByAuthorId(Integer authorId);
    
    public void deleteArticleByIdList(List<Integer> articleIdList);
}
