package com.company.scma.controller;

import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.UserBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserBizService userBizService;
    
    @RequiresPermissions("user:add")
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public Result createUser(@RequestBody CreateUserDTO createUserDTO){
        return userBizService.createUser(createUserDTO);
    }
}
