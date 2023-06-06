package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TPermission;

import java.util.List;

public interface PermissionService extends IService<TPermission> {
    public List<TPermission> getAllPermission();
    
    public List<TPermission> getPermissionByIdList(List<Integer> permissionIdList);
}
