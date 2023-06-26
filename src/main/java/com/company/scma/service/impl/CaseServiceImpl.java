package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetCaseDTO;
import com.company.scma.common.po.TCase;
import com.company.scma.mapper.CaseMapper;
import com.company.scma.service.mapperservice.CaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl extends ServiceImpl<CaseMapper, TCase> implements CaseService {
    @Autowired
    private CaseMapper caseMapper;
    @Override
    public void deleteCaseById(Integer caseId) {
        UpdateWrapper<TCase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.CASE_ID,caseId);
        caseMapper.update(null,updateWrapper);
    }

    @Override
    public IPage<TCase> getCaseByCondition(GetCaseDTO getCaseDTO) {
        QueryWrapper<TCase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.orderByDesc(Constant.ColumnName.CASE_ID);

        if (StringUtils.isNotEmpty(getCaseDTO.getKeyword())) {
            queryWrapper.like(Constant.ColumnName.KEYWORD, getCaseDTO.getKeyword());
        }

        if (StringUtils.isNotEmpty(getCaseDTO.getTitle())) {
            queryWrapper.like(Constant.ColumnName.TITLE, getCaseDTO.getTitle());
        }

        if (StringUtils.isNotEmpty(getCaseDTO.getPublishTime())) {
            queryWrapper.like(Constant.ColumnName.PUBLISH_TIME, getCaseDTO.getPublishTime());
        }

        if (ObjectUtil.isNotEmpty(getCaseDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getCaseDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getCaseDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getCaseDTO.getEndDate());
        }

        Page<TCase> page = Page.of(getCaseDTO.getCurrentPage(), getCaseDTO.getPageSize(), 0, true);
        IPage<TCase> tCaseIPage = caseMapper.selectPage(page, queryWrapper);
        return tCaseIPage;
    }

    @Override
    public TCase getTCaseById(Integer caseId) {
        QueryWrapper<TCase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.CASE_ID,caseId);
        TCase tCase = caseMapper.selectOne(queryWrapper);
        return tCase;
    }
}
