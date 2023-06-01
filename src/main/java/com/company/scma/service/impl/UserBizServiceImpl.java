package com.company.scma.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.UserBizService;
import com.company.scma.service.mapperservice.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserBizServiceImpl implements UserBizService {
    
    @Autowired
    private UserService userService;
    
    @Override
    @Transactional
    public Result createUser(CreateUserDTO createUserDTO) {
        //数据校验
        
        //密码解密
        
        //生成实体类
        TUser tUser = this.createaTUser(createUserDTO);
        //密码加密
        String password = tUser.getPassword();
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        tUser.setPassword(hashPassword);
        //入库
        userService.save(tUser);
        //返回
        return Result.success();
    }

    private TUser createaTUser(CreateUserDTO createUserDTO){
        TUser tUser = new TUser();
        BeanUtils.copyProperties(createUserDTO,tUser);
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tUser.setType(Constant.UserType.COMMON_USER);
        tUser.setBuildDate(new Date());
        tUser.setBuildUserid(currentUser.getUserid());
        tUser.setModifyDate(new Date());
        tUser.setModifyUserid(currentUser.getUserid());
        tUser.setDeleteflag(Constant.Judge.YES);
        tUser.setStatus(Constant.Judge.YES);
        return tUser;
    }
}
