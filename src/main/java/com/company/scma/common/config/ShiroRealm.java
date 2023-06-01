package com.company.scma.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.po.TPermission;
import com.company.scma.common.po.TUser;
import com.company.scma.service.mapperservice.PermissionService;
import com.company.scma.service.mapperservice.RoleMtmPermissionService;
import com.company.scma.service.mapperservice.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    
    @Autowired
    RoleMtmPermissionService roleMtmPermissionService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    UserService userService;
    
    //授权逻辑判断
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        TUser tUser = (TUser) principalCollection.getPrimaryPrincipal();
        //获取用户的roleid
        Integer roleId = tUser.getRoleId();
        //进行授权和处理
        Set<String> permssionSet = new HashSet<>();
        //查询角色和权限
        List<Integer> permissionIdList = roleMtmPermissionService.selectPermissionIdByRoleId(roleId);
        if(ObjectUtil.isEmpty(permissionIdList)){
            return authorizationInfo;
        }
        List<TPermission> TPermissionList = permissionService.getBaseMapper().selectBatchIds(permissionIdList);
        if(ObjectUtil.isEmpty(TPermissionList)){
            return authorizationInfo;
        }
        for (TPermission TPermission : TPermissionList) {
            if(ObjectUtil.isEmpty(TPermission)){
                continue;
            }
            String menuName = TPermission.getMenuName();
            String permissionName = TPermission.getPermissionName();
            String permissStr = menuName + ":" + permissionName;
            permssionSet.add(permissStr);
        }
        authorizationInfo.setStringPermissions(permssionSet);
        return authorizationInfo;
    }

    //登录逻辑判断
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        //判断账号是否存在
        TUser tUser = userService.selectUserByUsername(username);
        if(ObjectUtil.isEmpty(tUser)){
            throw new AuthenticationException();
        }
        //判断密码是否正确
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(tUser, tUser.getPassword(), this.getName());
        return simpleAuthenticationInfo;
    }
}
