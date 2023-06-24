package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateRoleDTO {
    /**
     * 权限名称
     */
    @NotBlank
    private String roleName;
    /**
     * 权限简介
     */
    private String introduction;
    /**
     * 权限id列表
     */
    @NotEmpty
    private List<Integer> permissionIdList;
}
