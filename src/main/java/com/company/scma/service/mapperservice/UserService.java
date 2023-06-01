package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TUser;

public interface UserService extends IService<TUser> {
    public TUser selectUserByUsername(String username);
}
