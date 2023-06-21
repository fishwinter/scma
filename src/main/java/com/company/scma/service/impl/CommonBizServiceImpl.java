package com.company.scma.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.CommonBizService;
import com.company.scma.service.mapperservice.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonBizServiceImpl implements CommonBizService {
    @Autowired
    private UserService userService;
    @Override
    public Result editPassword(String oldPassword,String newPassword) {
        Subject subject = SecurityUtils.getSubject();
        TUser tUser = (TUser) subject.getPrincipal();
        String dbPassword = tUser.getPassword();
        boolean flag = BCrypt.checkpw(oldPassword, dbPassword);
        if(!flag){
            return Result.getResult(ResultEnum.ERROR_PASSWORD);
        }
        String hashPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        tUser.setPassword(hashPassword);
        userService.saveOrUpdate(tUser);
        return Result.success();
    }
}
