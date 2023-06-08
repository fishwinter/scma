package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.po.TOperation;
import com.company.scma.mapper.OperationMapper;
import com.company.scma.service.mapperservice.OperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, TOperation> implements OperationService {
    @Autowired
    private OperationMapper operationMapper;
    
    @Override
    public void deleteOperationById(Integer operationId) {
        UpdateWrapper<TOperation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.OPERATION_ID,operationId);
        operationMapper.update(null,updateWrapper);
    }

    @Override
    public IPage<TOperation> getTOperationByCondition(GetOperationDTO getOperationDTO) {
        QueryWrapper<TOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.orderByDesc(Constant.ColumnName.OPERATION_ID);

        if (StringUtils.isNotEmpty(getOperationDTO.getOperationName())) {
            queryWrapper.eq(Constant.ColumnName.OPERATION_NAME, getOperationDTO.getOperationName());
        }

        if (StringUtils.isNotEmpty(getOperationDTO.getDirector())) {
            queryWrapper.eq(Constant.ColumnName.DIRECTOR, getOperationDTO.getDirector());
        }

        if (StringUtils.isNotEmpty(getOperationDTO.getContactTel())) {
            queryWrapper.eq(Constant.ColumnName.CONTACT_TEL, getOperationDTO.getContactTel());
        }

        if (ObjectUtil.isNotEmpty(getOperationDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getOperationDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getOperationDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getOperationDTO.getEndDate());
        }

        Page<TOperation> page = Page.of(getOperationDTO.getCurrentPage(), getOperationDTO.getPageSize(), 0, true);
        IPage<TOperation> tOperationIPage = operationMapper.selectPage(page, queryWrapper);
        return tOperationIPage;
    }

    @Override
    public TOperation getTOperationById(Integer operationId) {
        QueryWrapper<TOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.OPERATION_ID,operationId);
        List<TOperation> tOperationList = operationMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(tOperationList)){
            return tOperationList.get(0);
        }
        return null;
    }

    @Override
    public List<TOperation> getAllValidOperation() {
        QueryWrapper<TOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.and(wrapper -> wrapper.eq(Constant.ColumnName.STATUS,Constant.OperationStatus.NORMAL).or().eq(Constant.ColumnName.STATUS,Constant.OperationStatus.FINISH));
        return operationMapper.selectList(queryWrapper);
    }
}
