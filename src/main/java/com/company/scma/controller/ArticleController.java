package com.company.scma.controller;

import com.company.scma.common.dto.CreateArticleDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.ArticleBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "article", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {
    @Autowired
    private ArticleBizService articleBizService;
    
    @RequestMapping(value = "/createArticle", method = RequestMethod.POST)
    @RequiresPermissions("author:edit")
    public Result createArticle(@RequestBody CreateArticleDTO createArticleDTO){
        return articleBizService.createArticle(createArticleDTO);
    }

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    @RequiresPermissions("author:edit")
    public Result deleteArticle(@RequestBody List<Integer> articleIdList){
        return articleBizService.deleteArticle(articleIdList);
    }
}
