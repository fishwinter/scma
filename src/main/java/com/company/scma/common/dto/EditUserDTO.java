package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditUserDTO {
    @NotNull
    private Integer userid;
    
    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 姓名
     */
    @NotBlank
    private String name;

    /**
     * 性别
     */
    @NotNull
    private Integer sex;

    /**
     * 电话
     */
    @NotBlank
    private String tel;

    /**
     * 地址
     */
    @NotBlank
    private String addr;

    /**
     * 头像上传url
     */
    @NotBlank
    private String imageUrl;
    
    //角色id
    @NotNull
    private Integer roleId;
}
