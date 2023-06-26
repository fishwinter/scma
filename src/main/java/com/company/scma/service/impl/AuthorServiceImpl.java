package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetAuthorDTO;
import com.company.scma.common.po.TAuthor;
import com.company.scma.mapper.AuthorMapper;
import com.company.scma.service.mapperservice.AuthorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, TAuthor> implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;
    
    @Override
    public IPage<TAuthor> getAuthorByCondition(GetAuthorDTO getAuthorDTO) {
        QueryWrapper<TAuthor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.orderByDesc(Constant.ColumnName.AUTHOR_ID);

        if (StringUtils.isNotEmpty(getAuthorDTO.getName())) {
            queryWrapper.like(Constant.ColumnName.NAME, getAuthorDTO.getName());
        }

        if (StringUtils.isNotEmpty(getAuthorDTO.getTel())) {
            queryWrapper.like(Constant.ColumnName.TEL, getAuthorDTO.getTel());
        }

        if (StringUtils.isNotEmpty(getAuthorDTO.getWorkUnit())) {
            queryWrapper.like(Constant.ColumnName.WORK_UNIT, getAuthorDTO.getWorkUnit());
        }

        if (ObjectUtil.isNotEmpty(getAuthorDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getAuthorDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getAuthorDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getAuthorDTO.getEndDate());
        }

        Page<TAuthor> page = Page.of(getAuthorDTO.getCurrentPage(), getAuthorDTO.getPageSize(), 0, true);
        IPage<TAuthor> tAuthorIPage = authorMapper.selectPage(page, queryWrapper);
        return tAuthorIPage;
    }

    @Override
    public TAuthor getAuthorById(Integer authorId) {
        QueryWrapper<TAuthor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.AUTHOR_ID,authorId);
        TAuthor tAuthor = authorMapper.selectOne(queryWrapper);
        return tAuthor;
    }

    @Override
    public void deleteAuthorById(Integer authorId) {
        UpdateWrapper<TAuthor> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.AUTHOR_ID,authorId);
        authorMapper.update(null,updateWrapper);
    }
}
