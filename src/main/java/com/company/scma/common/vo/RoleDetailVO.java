package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleDetailVO {
    private String roleName;
    private String introduction;
    private List<MenuVO> rolePermission;
    private List<MenuVO> allPermission;
}
