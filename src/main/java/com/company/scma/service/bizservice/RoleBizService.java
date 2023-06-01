package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.common.vo.Result;

public interface RoleBizService {
    public Result createRole(CreateRoleDTO createRoleDTO);
    
    public Result getAllRole();
    
    public Result getRoleDetail(Integer roleId);
}
