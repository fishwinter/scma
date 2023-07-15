package com.company.scma.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class CreateSupplierDTO {
    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 联系地址
     */
    private String contactAddr;

    /**
     * 简介
     */
    private String introduction;

//    /**
//     * 企业类型，字典同会员类型
//     */
//    @NotNull
//    private Integer memberTypeId;

    /**
     * 单位类型
     */
    private Integer enterpriseTypeId;

    /**
     * 企业性质，字典同合作企业企业性质
     */
    private Integer partnershipType;

    /**
     * 企业微信号
     */
    private String wechatAccount;

    /**
     * 企业视频号
     */
    private String videoAccount;

    /**
     * 企业邮箱
     */
    private String email;

    /**
     * 负责人姓名
     */
    private String directorName;

    /**
     * 新建负责人职务类型名称
     */
    private String newDirectorPosition;

    /**
     * 负责人职务id
     */
    private Integer directorPositionId;

    /**
     * 企业logo url
     */
    private String logoUrl;

    /**
     * 新建联系人职务类型名称
     */
    private String newContactPosition;

    /**
     * 联系人职务id
     */
    private Integer contactPositionId;

    /**
     * 是否参加过物博会，0-否，1-是
     */
    private Integer joinLogisticsFair;

    /**
     * 服务对接人
     */
    private String serviceProvider;

    /**
     * 负责人手机号
     */
    private String directorTel;

    /**
     * 合作案例
     */
    private String cooperationCases;

    /**
     * 合作项目，1-协办，2-广告，3-活动
     */
    private List<Integer> projectType;

    /**
     * 首次加入协办单位时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date firstJoinTime;

    /**
     * 会长名称
     */
    private String presidentName;

    /**
     * 会长电话
     */
    private String presidentTel;

    /**
     * 秘书长名称
     */
    private String secretaryName;

    /**
     * 秘书长电话
     */
    private String secretaryTel;
}
