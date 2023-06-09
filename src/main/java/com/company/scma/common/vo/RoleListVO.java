package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleListVO {
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 创建时间
     */
    private Date buildDate;
}
