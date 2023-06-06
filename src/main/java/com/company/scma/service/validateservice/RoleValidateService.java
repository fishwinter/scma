package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.common.dto.EditRoleDTO;
import com.company.scma.common.vo.Result;

public interface RoleValidateService {
    public boolean validateCreateRoleDTO (CreateRoleDTO createRoleDTO);

    public boolean validateEditRoleDTO (EditRoleDTO editRoleDTO);
    
    public Result validateDeleteRoleId(Integer roleId);
}
