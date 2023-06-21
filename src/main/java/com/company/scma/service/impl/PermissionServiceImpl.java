package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TPermission;
import com.company.scma.mapper.PermissionMapper;
import com.company.scma.service.mapperservice.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,TPermission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    
    @Override
    public List<TPermission> getAllPermission() {
        QueryWrapper<TPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        List<TPermission> tPermissionList = permissionMapper.selectList(queryWrapper);
        return tPermissionList;
    }

    @Override
    public List<TPermission> getPermissionByIdList(List<Integer> permissionIdList) {
        if(ObjectUtil.isEmpty(permissionIdList)){
            return null;
        }
        QueryWrapper<TPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.in(Constant.ColumnName.PERMISSION_ID,permissionIdList);
        return permissionMapper.selectList(queryWrapper);
    }
}
