package com.company.scma.controller.one;

import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.dto.EditUserDTO;
import com.company.scma.common.dto.GetUserDTO;
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

    @RequiresPermissions("user:visit")
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public Result getUser(@RequestBody GetUserDTO getUserDTO){
        return userBizService.getUser(getUserDTO);
    }

    @RequiresPermissions("user:visit")
    @RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
    public Result getUserDetail(@RequestParam Integer userid){
        return userBizService.getUserDetail(userid);
    }

    @RequiresPermissions("user:visit")
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    public Result checkUserName(@RequestParam String username){
        return userBizService.checkUserName(username);
    }
    
    @RequiresPermissions("user:add")
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public Result createUser(@RequestBody CreateUserDTO createUserDTO){
        return userBizService.createUser(createUserDTO);
    }

    @RequiresPermissions("user:edit")
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public Result editUser(@RequestBody EditUserDTO editUserDTO){
        return userBizService.editUser(editUserDTO);
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Result deleteUser(@RequestParam Integer userid){
        return userBizService.deleteUser(userid);
    }
}
