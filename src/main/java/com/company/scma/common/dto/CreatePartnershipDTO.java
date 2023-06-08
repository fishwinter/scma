package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreatePartnershipDTO {
    /**
     * 单位名称
     */
    private String partnershipName;

    /**
     * 企业性质，1-国企，2-民企，3-外企
     */
    private Integer partnershipType;

    /**
     * 企业logo url
     */
    private String logoUrl;

    /**
     * 地址
     */
    private String addr;

    /**
     * 企业邮箱
     */
    private String email;

    /**
     * 官方网址
     */
    private String webAddr;

    /**
     * 微信公众号
     */
    private String wechatAccount;

    /**
     * 视频号
     */
    private String videoAccount;

    /**
     * 负责人姓名
     */
    private String directorName;

    /**
     * 负责人职务
     */
    private String directorPosition;

    /**
     * 负责人手机号
     */
    private String directorTel;

    /**
     * 负责人微信号
     */
    private String directorWechat;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人职务
     */
    private String contactPosition;

    /**
     * 联系人手机
     */
    private String contactTel;

    /**
     * 联系人微信号
     */
    private String contactWechat;

    /**
     * 协办金额
     */
    private BigDecimal amount;

    /**
     * 进账时间
     */
    private Date paymentTime;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 合同期限
     */
    private String contractTerm;

    /**
     * 合作项目，1-协办，2-广告，3-活动
     */
    private String projectType;

    /**
     * 首次加入协办单位时间
     */
    private Date firstJoinTime;

    /**
     * 对接服务人员
     */
    private String serviceProvider;

    /**
     * 当前活动id
     */
    @NotNull
    private Integer operationId;
    
    @NotBlank
    private String managerUsername;
    
    @NotBlank
    private String managerPassword;
    
    @NotNull
    private Integer subAccountNum;
}
