package com.company.scma.controller;

import cn.hutool.crypto.digest.BCrypt;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    
    @PostMapping("/login")
    public Result login(String username, String password, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            TUser tUser = (TUser) subject.getPrincipal();
            session.setAttribute(Constant.Common.SESSION_KEY, tUser.getUsername());
            return Result.success();
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
    
    
}
