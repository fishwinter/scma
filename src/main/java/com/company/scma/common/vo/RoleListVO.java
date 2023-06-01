package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleListVO {
    private Integer roleId;
    private String roleName;
    private Date buildDate;
}
