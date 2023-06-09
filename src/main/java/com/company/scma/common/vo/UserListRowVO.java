package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserListRowVO {
    /**
     * 用户id
     */
    private Integer userid;
    /**
     * 姓名
     */
    private String name;
    /**
     * 账号
     */
    private String username;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date buildDate;
}
