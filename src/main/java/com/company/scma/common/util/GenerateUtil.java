package com.company.scma.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.common.po.TPermission;
import com.company.scma.common.po.TRole;
import com.company.scma.common.po.TRoleMtmPermission;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.MenuVO;
import com.company.scma.common.vo.PermissionVO;
import com.company.scma.common.vo.RoleDetailVO;
import com.company.scma.common.vo.RoleListVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GenerateUtil {
    public static RoleListVO getRoleListVOByTRole(TRole tRole) {
        RoleListVO roleListVO = new RoleListVO();
        BeanUtils.copyProperties(tRole, roleListVO);
        return roleListVO;
    }

    public static TRole createTRole(CreateRoleDTO createRoleDTO) {
        TRole tRole = new TRole();
        tRole.setRoleName(createRoleDTO.getRoleName());
        tRole.setIntroduction(createRoleDTO.getIntroduction());
        //获取当前登录用户信息
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tRole.setBuildDate(new Date());
        tRole.setBuildUserid(tUser.getUserid());
        tRole.setModifyDate(new Date());
        tRole.setModifyUserid(tUser.getUserid());
        tRole.setDeleteflag(Constant.Judge.YES);
        return tRole;
    }

    public static List<TRoleMtmPermission> createTRoleMtmPermissionList(TRole tRole, CreateRoleDTO createRoleDTO) {
        List<Integer> permissionIdList = createRoleDTO.getPermissionIdList();
        ArrayList<TRoleMtmPermission> tRoleMtmPermissionList = new ArrayList<>();
        for (Integer permissionId : permissionIdList) {
            TRoleMtmPermission tRoleMtmPermission = new TRoleMtmPermission();
            tRoleMtmPermission.setRoleId(tRole.getRoleId());
            tRoleMtmPermission.setPermissionId(permissionId);
            tRoleMtmPermission.setDeleteflag(Constant.Judge.YES);
            tRoleMtmPermissionList.add(tRoleMtmPermission);
        }
        return tRoleMtmPermissionList;
    }

    public static RoleDetailVO createRoleDetailVO(TRole tRole, List<TPermission> tPermissionList){
        RoleDetailVO roleDetailVO = new RoleDetailVO();
        roleDetailVO.setRoleName(tRole.getRoleName());
        roleDetailVO.setIntroduction(tRole.getIntroduction());
        List<MenuVO> menuVOList = GenerateUtil.createMenuVOByTPermissionList(tPermissionList);
        roleDetailVO.setMenuVOList(menuVOList);
        return roleDetailVO;
    }
    
    public static List<MenuVO> createMenuVOByTPermissionList(List<TPermission> tPermissionList){
        Map<String, MenuVO> menuVOMap = new HashMap<String, MenuVO>();
        for (TPermission tPermission : tPermissionList) {
            String menuName = tPermission.getMenuName();
            PermissionVO permissionVO = GenerateUtil.getPermissionVOByTPermission(tPermission);
            if(menuVOMap.containsKey(menuName)){
                MenuVO menuVO = menuVOMap.get(menuName);
                List<PermissionVO> permissionVOList = menuVO.getPermissionVOList();
                if(ObjectUtil.isEmpty(permissionVOList)){
                    permissionVOList = new ArrayList<>();
                }
                permissionVOList.add(permissionVO);
            }else{
                MenuVO menuVO = new MenuVO();
                menuVOMap.put(tPermission.getMenuName(),menuVO);
                menuVO.setMenuName(tPermission.getMenuName());
                menuVO.setPermissionVOList(new ArrayList<>());
                menuVO.getPermissionVOList().add(permissionVO);
            }
        }
        List<MenuVO> menuVOList = menuVOMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return menuVOList;
    }
    
    public static PermissionVO getPermissionVOByTPermission(TPermission tPermission){
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setPermissionId(tPermission.getPermissionId());
        permissionVO.setPermissionName(tPermission.getPermissionName());
        return permissionVO;
    } 
}
