package com.company.scma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TArticle;
import com.company.scma.mapper.ArticleMapper;
import com.company.scma.service.mapperservice.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, TArticle> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<TArticle> getTArticleByAuthorId(Integer authorId) {
        QueryWrapper<TArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.AUTHOR_ID,authorId);
        List<TArticle> tArticleList = articleMapper.selectList(queryWrapper);
        return tArticleList;
    }

    @Override
    public void deleteArticleByIdList(List<Integer> articleIdList) {
        UpdateWrapper<TArticle> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.in(Constant.ColumnName.ARTICLE_ID,articleIdList);
        articleMapper.update(null,updateWrapper);
    }
}
