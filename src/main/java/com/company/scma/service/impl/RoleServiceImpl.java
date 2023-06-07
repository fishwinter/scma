package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TRole;
import com.company.scma.common.po.TRoleMtmPermission;
import com.company.scma.mapper.RoleMapper;
import com.company.scma.service.mapperservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, TRole> implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public void deleteRoleByRoleId(Integer roleId) {
        UpdateWrapper<TRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.ROLE_ID,roleId);
        roleMapper.update(null,updateWrapper);
    }

    @Override
    public List<TRole> getAllRole() {
        QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        List<TRole> tRoleList = roleMapper.selectList(queryWrapper);
        return tRoleList;
    }

    @Override
    public TRole getRoleByRoleId(Integer roleId) {
        QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.ROLE_ID,roleId);
        List<TRole> tRoleList = roleMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(tRoleList)){
            return tRoleList.get(0);
        }
        return null;
    }
}
