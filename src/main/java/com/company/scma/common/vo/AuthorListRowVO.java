package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorListRowVO {
    /**
     * 作者id
     */
    private Integer authorId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 职务
     */
    private String position;

    /**
     * 创建时间
     */
    private Date buildDate;
}
