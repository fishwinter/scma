package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailVO {
    /**
     * 账号
     */
    private String username;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 联系地址
     */
    private String addr;
    /**
     * 头像url
     */
    private String imageUrl;
    /**
     * 当前角色信息
     */
    private RoleListVO myRole;
    /**
     * 所有角色信息
     */
    private List<RoleListVO> allRole;
}
