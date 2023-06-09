package com.company.scma.service.impl;

import com.company.scma.common.po.TPermission;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.MenuVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.PermissionBizService;
import com.company.scma.service.mapperservice.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionBizServiceImpl implements PermissionBizService {
    @Autowired
    private PermissionService permissionService;
    
    @Override
    public Result getAllPermission() {
        List<TPermission> allPermission = permissionService.getAllPermission();
        List<MenuVO> menuVOList = GenerateUtil.createMenuVOByTPermissionList(allPermission);
        return Result.success(menuVOList);
    }
}
