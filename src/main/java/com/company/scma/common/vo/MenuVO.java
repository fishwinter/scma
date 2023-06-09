package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 菜单序号
     */
    private Integer menuSort;
    /**
     * 权限信息
     */
    private List<PermissionVO> permissionVOList;
}
