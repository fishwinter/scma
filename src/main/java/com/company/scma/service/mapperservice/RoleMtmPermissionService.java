package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TRoleMtmPermission;

import java.util.List;

public interface RoleMtmPermissionService extends IService<TRoleMtmPermission> {
    
    List<Integer> selectPermissionIdByRoleId(Integer roleid);
}
