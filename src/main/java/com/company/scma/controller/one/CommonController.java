package com.company.scma.controller.one;

import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.CommonBizService;
import com.company.scma.task.ScheduledTask;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "common", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonController {
    @Autowired
    private CommonBizService commonBizService;
    @Autowired
    private ScheduledTask scheduledTask;
    @PostMapping("/getHeadUrl")
    public Result getHeadUrl(){
        Subject subject = SecurityUtils.getSubject();
        TUser tUser = (TUser) subject.getPrincipal();
        return Result.success(tUser.getImageUrl());
    }

    @PostMapping("/editPassword")
    public Result editPassword(@RequestParam String oldPassword,@RequestParam String newPassword){
        return commonBizService.editPassword(oldPassword,newPassword);
    }

    @PostMapping("/getUsername")
    public Result getUsername(){
        return commonBizService.getUsername();
    }

    @PostMapping("/operateTask")
    public Result operateTask(){
        scheduledTask.scheduledTask();
        return Result.success();
    }
}

