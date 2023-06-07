package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailVO {
    private String username;
    private String sex;
    private String name;
    private String tel;
    private String addr;
    private String imageUrl;
    private RoleListVO myRole;
    private List<RoleListVO> allRole;
}
