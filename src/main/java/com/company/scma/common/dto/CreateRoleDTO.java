package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateRoleDTO {
    @NotBlank
    private String roleName;
    @NotBlank
    private String introduction;
    @NotEmpty
    private List<Integer> permissionIdList;
}
