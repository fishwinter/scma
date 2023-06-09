package com.company.scma.common.vo;

import lombok.Data;

@Data
public class PermissionVO {
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 权限序号
     */
    private Integer permissionSort;
    /**
     * 权限名称
     */
    private String permissionName;
}
