package com.company.scma.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.po.TPermission;
import com.company.scma.mapper.PermissionMapper;
import com.company.scma.service.mapperservice.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, TPermission> implements PermissionService {
}
