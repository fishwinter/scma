package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EditRoleDTO {
    /**
     * 角色id
     */
    @NotNull
    private Integer roleId;
    /**
     * 角色名称
     */
    @NotBlank
    private String roleName;
    /**
     * 角色介绍
     */
    @NotBlank
    private String introduction;
    /**
     * 权限id列表
     */
    @NotEmpty
    private List<Integer> permissionIdList;
}
