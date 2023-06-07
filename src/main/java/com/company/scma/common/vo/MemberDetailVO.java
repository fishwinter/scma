package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MemberDetailVO {
    /**
     * 会员名称
     */
    private String memberName;

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

    private MemberTypeVO myMemberType;
    
    private List<MemberTypeVO> allMemberType;
    
    private List<ItemVO> myItem;
    
    private List<ItemVO> allItem;

    private String cityCode;

    /**
     * 简介
     */
    private String introduction;

}
