package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleDetailVO {
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 当前角色信息
     */
    private List<MenuVO> rolePermission;
    /**
     * 所有角色信息
     */
    private List<MenuVO> allPermission;
}
