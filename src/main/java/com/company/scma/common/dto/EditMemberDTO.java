package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class EditMemberDTO {
    @NotNull
    private Integer memberId;
    /**
     * 会员名称
     */
    @NotBlank
    private String memberName;

    /**
     * 会员类型serialno
     */
    @NotNull
    private Integer memberTypeId;

    /**
     * 法人
     */
    private String corporation;

    /**
     * 注册地址
     */
    private String regAddress;

    /**
     * 创建日期
     */
    private Date steupdate;

    /**
     * 统一社会信用代码
     */
    private String creditno;

    /**
     * 注册资本
     */
    private String regstock;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人职务
     */
    private String contactTitle;

    /**
     * 联系人电话
     */
    private String contactTel;

    /**
     * 联系人地址
     */
    private String contactAddr;

    /**
     * 省区域编码
     */
    private String provinceCode;

    /**
     * 市区域编码
     */
    private String cityCode;

    /**
     * 简介
     */
    private String introduction;
}
