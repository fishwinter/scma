package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {
    private String menuName;
    private List<PermissionVO> permissionVOList;
}
