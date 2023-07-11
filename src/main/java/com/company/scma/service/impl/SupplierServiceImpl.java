package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetSupplierDTO;
import com.company.scma.common.po.TSupplier;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.mapper.SupplierMapper;
import com.company.scma.service.mapperservice.SupplierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, TSupplier> implements SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Override
    public TSupplier getTSupplierById(Integer supplierId) {
        QueryWrapper<TSupplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.SUPPLIER_ID,supplierId);
        TSupplier tSupplier = supplierMapper.selectOne(queryWrapper);
        return tSupplier;
    }

    @Override
    public IPage<TSupplier> getTSupplierByCondition(GetSupplierDTO getSupplierDTO) {
        QueryWrapper<TSupplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.orderByDesc(Constant.ColumnName.SUPPLIER_ID);

        if (StringUtils.isNotEmpty(getSupplierDTO.getSupplierName())) {
            queryWrapper.like(Constant.ColumnName.SUPPLIER_NAME, getSupplierDTO.getSupplierName());
        }

        if (StringUtils.isNotEmpty(getSupplierDTO.getContacts())) {
            queryWrapper.like(Constant.ColumnName.CONTACTS, getSupplierDTO.getContacts());
        }

        if (StringUtils.isNotEmpty(getSupplierDTO.getContactTel())) {
            queryWrapper.like(Constant.ColumnName.CONTACT_TEL, getSupplierDTO.getContactTel());
        }

        if (StringUtils.isNotEmpty(getSupplierDTO.getServiceProvider())) {
            queryWrapper.like(Constant.ColumnName.SERVICE_PROVIDER, getSupplierDTO.getServiceProvider());
        }

        List<Integer> projectType = getSupplierDTO.getProjectType();
        if(ObjectUtil.isNotEmpty(projectType)){
            queryWrapper.and(wrapper -> {
                for (int i = 0; i < projectType.size(); i++) {
                    wrapper.like(Constant.ColumnName.PROJECT_TYPE,projectType.get(i));
                    if(i < projectType.size() - 1){
                        wrapper.or();
                    }
                }
            });
        }

        if (ObjectUtil.isNotEmpty(getSupplierDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getSupplierDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getSupplierDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getSupplierDTO.getEndDate());
        }

        Page<TSupplier> page = Page.of(getSupplierDTO.getCurrentPage(), getSupplierDTO.getPageSize(), 0, true);
        IPage<TSupplier> tSupplierIPage = supplierMapper.selectPage(page,queryWrapper);
        return tSupplierIPage;
    }

    @Override
    public void deleteSupplierById(Integer supplierId) {
        UpdateWrapper<TSupplier> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.ColumnName.SUPPLIER_ID,supplierId);
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        supplierMapper.update(null,updateWrapper);
    }

    @Override
    public TSupplier getTSupplierByName(String supplierName) {
        QueryWrapper<TSupplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.SUPPLIER_NAME,supplierName);
        TSupplier tSupplier = supplierMapper.selectOne(queryWrapper);
        return tSupplier;    }
}
