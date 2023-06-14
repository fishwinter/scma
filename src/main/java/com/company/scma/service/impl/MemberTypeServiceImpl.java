package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TMemberType;
import com.company.scma.mapper.MemberTypeMapper;
import com.company.scma.service.mapperservice.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberTypeServiceImpl extends ServiceImpl<MemberTypeMapper, TMemberType> implements MemberTypeService {
    @Autowired
    private MemberTypeMapper memberTypeMapper;

    @Override
    public TMemberType getMemberTypeByMemberTypeId(Integer memberTypeId) {
        QueryWrapper<TMemberType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.MEMBER_TYPE_ID, memberTypeId);
        List<TMemberType> tMemberTypeList = memberTypeMapper.selectList(queryWrapper);
        if (ObjectUtil.isNotEmpty(tMemberTypeList)) {
            return tMemberTypeList.get(0);
        }
        return null;
    }

    @Override
    public List<TMemberType> getAllType() {
        QueryWrapper<TMemberType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        return memberTypeMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteMemberTypeById(Integer memberTypeId) {
        UpdateWrapper<TMemberType> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.MEMBER_TYPE_ID,memberTypeId);
        memberTypeMapper.update(null,updateWrapper);
    }
}
