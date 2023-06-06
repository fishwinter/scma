package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserListRowVO {
    private Integer userid;
    private String name;
    private String username;
    private String tel;
    private Integer status;
    private Date buildDate;
}
