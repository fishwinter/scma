package com.company.scma.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.po.TRole;
import com.company.scma.mapper.RoleMapper;
import com.company.scma.service.mapperservice.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, TRole> implements RoleService {
}
