package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TRole;

import java.util.List;

public interface RoleService extends IService<TRole> {
    
    public void deleteRoleByRoleId(Integer roleId);
    
    public List<TRole> getAllRole();
    
    public TRole getRoleByRoleId(Integer roleId);
}
