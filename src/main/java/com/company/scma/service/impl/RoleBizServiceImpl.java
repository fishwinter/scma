package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.common.dto.EditRoleDTO;
import com.company.scma.common.po.TPermission;
import com.company.scma.common.po.TRole;
import com.company.scma.common.po.TRoleMtmPermission;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.RoleDetailVO;
import com.company.scma.common.vo.RoleListVO;
import com.company.scma.service.bizservice.RoleBizService;
import com.company.scma.service.mapperservice.PermissionService;
import com.company.scma.service.mapperservice.RoleMtmPermissionService;
import com.company.scma.service.mapperservice.RoleService;
import com.company.scma.service.validateservice.RoleValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleBizServiceImpl implements RoleBizService {

    @Autowired
    private RoleValidateService roleValidateService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMtmPermissionService roleMtmPermissionService;
    @Autowired
    private PermissionService permissionService;
    
    @Override
    @Transactional
    public Result createRole(CreateRoleDTO createRoleDTO) {
        //参数校验
        boolean flag = roleValidateService.validateCreateRoleDTO(createRoleDTO);
        if (!flag) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //生成角色信息并插入
        TRole tRole = GenerateUtil.createTRole(createRoleDTO);
        roleService.save(tRole);
        //生成角色，权限关联信息并插入
        List<TRoleMtmPermission> tRoleMtmPermissionList = GenerateUtil.createTRoleMtmPermissionList(tRole, createRoleDTO);
        roleMtmPermissionService.saveBatch(tRoleMtmPermissionList);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result editRole(EditRoleDTO editRoleDTO) {
        //参数校验
        boolean flag = roleValidateService.validateEditRoleDTO(editRoleDTO);
        if (!flag) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //更新role表
        TRole tRole = GenerateUtil.createTRole(editRoleDTO);
        roleService.saveOrUpdate(tRole);
        //删除role之前对应的permission关联信息
        roleMtmPermissionService.deleteByRoleId(editRoleDTO.getRoleId());
        //插入新的关联信息
        List<TRoleMtmPermission> tRoleMtmPermissionList = GenerateUtil.createTRoleMtmPermissionList(editRoleDTO);
        roleMtmPermissionService.saveBatch(tRoleMtmPermissionList);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteRole(Integer roleId) {
        //参数校验
        Result result = roleValidateService.validateDeleteRoleId(roleId);
        if(!Result.isSuccess(result)){
            return result;
        }
        //删除角色
        roleService.deleteRoleByRoleId(roleId);
        //删除对应关系
        roleMtmPermissionService.deleteByRoleId(roleId);
        //返回
        return Result.success();
    }

    @Override
    public Result getAllRole() {
        List<RoleListVO> roleListVOList = new ArrayList<>();
        //查询角色
        List<TRole> tRoleList = roleService.getBaseMapper().selectList(null);
        //封装返回值
        if (ObjectUtil.isNotEmpty(tRoleList)) {
            roleListVOList = tRoleList.stream().map(GenerateUtil::getRoleListVOByTRole).collect(Collectors.toList());
        }
        //返回
        return Result.success(roleListVOList);
    }

    @Override
    public Result getRoleDetail(Integer roleId) {
        RoleDetailVO roleDetailVO = new RoleDetailVO();
        //参数校验
        if(ObjectUtil.isEmpty(roleId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //查询roleid对应的permissionid
        TRole tRole = roleService.getById(roleId);
        List<Integer> permissionIdList = roleMtmPermissionService.selectPermissionIdByRoleId(roleId);
        //查询permission信息
        if(ObjectUtil.isEmpty(tRole) || ObjectUtil.isEmpty(permissionIdList)){
            return Result.success(roleDetailVO);
        }
        List<TPermission> rolePermissionList = permissionService.getPermissionByIdList(permissionIdList);
        List<TPermission> allPermissionList = permissionService.getAllPermission();
        //封装返回结果
        if(ObjectUtil.isEmpty(rolePermissionList) || ObjectUtil.isEmpty(allPermissionList) ){
            return Result.success(roleDetailVO);
        }
        roleDetailVO = GenerateUtil.createRoleDetailVO(tRole, rolePermissionList, allPermissionList);
        //返回
        return Result.success(roleDetailVO);
    }
}
