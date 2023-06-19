package com.company.scma.controller;

import cn.hutool.crypto.digest.BCrypt;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.po.TPermission;
import com.company.scma.common.po.TUser;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.MenuVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.PermissionService;
import com.company.scma.service.mapperservice.RoleMtmPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleMtmPermissionService roleMtmPermissionService;
    
    @PostMapping("/login")
    public Result login(String username, String password, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            TUser tUser = (TUser) subject.getPrincipal();
            session.setAttribute(Constant.Common.SESSION_KEY, tUser.getUsername());
            List<Integer> permissionIdList = roleMtmPermissionService.selectPermissionIdByRoleId(tUser.getRoleId());
            List<TPermission> permissionList = permissionService.getPermissionByIdList(permissionIdList);
            List<MenuVO> menuVOList = GenerateUtil.createMenuVOByTPermissionList(permissionList);
            return Result.success(menuVOList);
        }catch (Exception e){
            return Result.getResult(ResultEnum.ERROR_USERNAME_OR_PASSWORD);
        }
    }

    @PostMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }

    @PostMapping("/getHeadUrl")
    public Result getHeadUrl(){
        Subject subject = SecurityUtils.getSubject();
        TUser tUser = (TUser) subject.getPrincipal();
        return Result.success(tUser.getImageUrl());
    }
    
    
}
