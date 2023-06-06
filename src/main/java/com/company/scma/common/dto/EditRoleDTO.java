package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EditRoleDTO {
    @NotNull
    private Integer roleId;
    @NotBlank
    private String roleName;
    @NotBlank
    private String introduction;
    @NotEmpty
    private List<Integer> permissionIdList;
}
