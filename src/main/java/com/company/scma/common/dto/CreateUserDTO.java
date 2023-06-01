package com.company.scma.common.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String addr;

    /**
     * 头像上传url
     */
    private String imageUrl;
    
    //角色id
    private Integer roleId;
}
