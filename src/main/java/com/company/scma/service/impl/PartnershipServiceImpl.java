package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.po.TPartnership;
import com.company.scma.common.po.TUser;
import com.company.scma.mapper.PartnershipMapper;
import com.company.scma.service.mapperservice.PartnershipService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnershipServiceImpl extends ServiceImpl<PartnershipMapper, TPartnership> implements PartnershipService {
    @Autowired
    private PartnershipMapper partnershipMapper;
    @Override
    public IPage<TPartnership> getPartnershipByCondition(GetPartnershipDTO getPartnershipDTO) {
        QueryWrapper<TPartnership> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        if(ObjectUtil.isEmpty(currentUser) || ObjectUtil.isEmpty(currentUser.getUserid())){
            return null;
        }
        queryWrapper.orderByDesc(Constant.ColumnName.PARTNERSHIP_ID);

        if (StringUtils.isNotEmpty(getPartnershipDTO.getPartnershipName())) {
            queryWrapper.like(Constant.ColumnName.PARTNERSHIP_NAME, getPartnershipDTO.getPartnershipName());
        }

        if (StringUtils.isNotEmpty(getPartnershipDTO.getContactName())) {
            queryWrapper.like(Constant.ColumnName.CONTACT_NAME, getPartnershipDTO.getContactName());
        }

        if (StringUtils.isNotEmpty(getPartnershipDTO.getContactTel())) {
            queryWrapper.like(Constant.ColumnName.CONTACT_TEL, getPartnershipDTO.getContactTel());
        }

        if (ObjectUtil.isNotEmpty(getPartnershipDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getPartnershipDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getPartnershipDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getPartnershipDTO.getEndDate());
        }

        Page<TPartnership> page = Page.of(getPartnershipDTO.getCurrentPage(), getPartnershipDTO.getPageSize(), 0, true);
        IPage<TPartnership> tPartnershipIPage = partnershipMapper.selectPage(page,queryWrapper);
        return tPartnershipIPage;
    }

    @Override
    public TPartnership getTPartnershipById(Integer partnershipId) {
        QueryWrapper<TPartnership> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES); 
        queryWrapper.eq(Constant.ColumnName.PARTNERSHIP_ID, partnershipId);
        return partnershipMapper.selectOne(queryWrapper);
    }

    @Override
    public void deleteByPartnershipId(Integer partnershipId) {
        UpdateWrapper<TPartnership> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.PARTNERSHIP_ID,partnershipId);
        partnershipMapper.update(null,updateWrapper);
    }

    @Override
    public List<TPartnership> getTPartnershipByName(String partnershipName) {
        QueryWrapper<TPartnership> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.PARTNERSHIP_NAME,partnershipName);
        return partnershipMapper.selectList(queryWrapper);
    }

    @Override
    public List<TPartnership> getTPartnershipByOperationId(Integer operationId,Integer deleteflag) {
        QueryWrapper<TPartnership> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.OPERATION_ID,operationId);
        if(ObjectUtil.isNotEmpty(deleteflag)){
            queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        }
        return partnershipMapper.selectList(queryWrapper);
    }

    @Override
    public List<TPartnership> fuzzQueryTPartnershipByPartnershipName(String partnershipName) {
        QueryWrapper<TPartnership> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES); 
        queryWrapper.like(Constant.ColumnName.PARTNERSHIP_NAME,partnershipName);
        return partnershipMapper.selectList(queryWrapper);
    }
}
