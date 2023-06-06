package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TRoleMtmPermission;
import com.company.scma.mapper.RoleMtmPermissionMapper;
import com.company.scma.service.mapperservice.RoleMtmPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMtmPermissionServiceImpl extends ServiceImpl<RoleMtmPermissionMapper, TRoleMtmPermission> implements RoleMtmPermissionService {
    
    @Autowired
    private RoleMtmPermissionMapper roleMtmPermissionMapper;
    
    @Override
    public List<Integer> selectPermissionIdByRoleId(Integer roleid) {
        List<Integer> result = new ArrayList<>();
        QueryWrapper<TRoleMtmPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(Constant.ColumnName.PERMISSION_ID);
        queryWrapper.eq(Constant.ColumnName.ROLE_ID,roleid);
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        List<TRoleMtmPermission> TRoleMtmPermissionList = roleMtmPermissionMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(TRoleMtmPermissionList)){
            result = TRoleMtmPermissionList.stream().map(TRoleMtmPermission::getPermissionId).collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        UpdateWrapper<TRoleMtmPermission> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.ROLE_ID,roleId);
        roleMtmPermissionMapper.update(null,updateWrapper);
    }
}
