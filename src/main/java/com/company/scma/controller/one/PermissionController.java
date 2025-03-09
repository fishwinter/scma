package com.company.scma.controller.one;

import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.PermissionBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "permission", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController {
    @Autowired
    private PermissionBizService permissionBizService;

    @RequestMapping(value = "/getAllPermission", method = RequestMethod.POST)
    @RequiresPermissions("role:add")
    public Result getAllPermission(){
        return permissionBizService.getAllPermission();
    }
}
