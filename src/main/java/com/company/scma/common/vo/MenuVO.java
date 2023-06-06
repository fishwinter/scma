package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {
    private String menuName;
    private Integer menuSort;
    private List<PermissionVO> permissionVOList;
}
