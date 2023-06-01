package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TUser;
import com.company.scma.mapper.UserMapper;
import com.company.scma.service.mapperservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TUser> implements UserService {
    
    @Autowired
    UserMapper userMapper;

    @Override
    public TUser selectUserByUsername(String username) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.USERNAME,username);
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.STATUS,Constant.Judge.YES);
        List<TUser> tUserList = userMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(tUserList)){
            return tUserList.get(0);
        }
        return null;
    }
}
