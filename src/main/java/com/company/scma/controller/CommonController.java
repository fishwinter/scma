package com.company.scma.controller;

import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "common", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonController {
    @PostMapping("/getHeadUrl")
    public Result getHeadUrl(){
        Subject subject = SecurityUtils.getSubject();
        TUser tUser = (TUser) subject.getPrincipal();
        return Result.success(tUser.getImageUrl());
    }
}
