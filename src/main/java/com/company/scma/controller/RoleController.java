package com.company.scma.controller;

import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.common.dto.EditRoleDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.RoleBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    
    @Autowired
    private RoleBizService roleBizService;
    
    @RequestMapping(value = "/getAllRole", method = RequestMethod.POST)
    @RequiresPermissions("role:visit")
    public Result getAllRole(){
        return roleBizService.getAllRole();
    }

    @RequestMapping(value = "/getRoleDetail", method = RequestMethod.POST)
    @RequiresPermissions("role:visit")
    public Result getRoleDetail(@RequestParam Integer roleId){
        return roleBizService.getRoleDetail(roleId);
    }
    
    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
    @RequiresPermissions("role:add")
    public Result createRole(@RequestBody CreateRoleDTO createRoleDTO){
        return roleBizService.createRole(createRoleDTO);
    }

    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    @RequiresPermissions("role:edit")
    public Result editRole(@RequestBody EditRoleDTO editRoleDTO){
        return roleBizService.editRole(editRoleDTO);
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @RequiresPermissions("role:delete")
    public Result deleteRole(@RequestParam Integer roleId){
        return roleBizService.deleteRole(roleId);
    }
}
