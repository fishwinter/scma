package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateArticleDTO;
import com.company.scma.common.po.TArticle;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.ArticleBizService;
import com.company.scma.service.mapperservice.ArticleService;
import com.company.scma.service.validateservice.ArticleValidateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleBizServiceImpl implements ArticleBizService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleValidateService articleValidateService;
    
    @Override
    @Transactional
    public Result createArticle(CreateArticleDTO createArticleDTO) {
        //参数校验
        Result result = articleValidateService.validateCreateArticleDTO(createArticleDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据转换
        TArticle tArticle = GenerateUtil.getTArticle(createArticleDTO);
        //入库
        articleService.save(tArticle);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteArticle(List<Integer> articleIdList) {
        //参数校验
        if(ObjectUtil.isEmpty(articleIdList)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //删除
        articleService.deleteArticleByIdList(articleIdList);
        //返回
        return Result.success();
    }
}
