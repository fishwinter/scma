package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SupplierDetailVO {
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

    /**
     * 当前会员类型
     */
    private MemberTypeVO myMemberType;

    /**
     * 所有会员类型
     */
    private List<MemberTypeVO> allMemberType;

    /**
     * 当前合作企业的企业性质，1-国企，2-民企，3-外企
     */
    private PartnershipTypeVO myPartnershipType;

    /**
     * 所有合作企业性质
     */
    private List<PartnershipTypeVO> allPartnershipType;

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
     * 所有职务
     */
    private List<PositionVO> allPosition;

    /**
     * 负责人职务
     */
    private String directorPosition;

    /**
     * 企业logo url
     */
    private String logoUrl;

    /**
     * 联系人职务
     */
    private PositionVO contactsPosition;

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
     * 当前合作企业合作项目类型，1-协办，2-广告，3-活动
     */
    private List<PartnershipProjectTypeVO> myProjectType;

    /**
     * 所有合作企业合作项目类型，1-协办，2-广告，3-活动
     */
    private List<PartnershipProjectTypeVO> allProjectType;

    /**
     * 首次加入协办单位时间
     */
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
