package com.company.scma.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class EditPartnershipDTO {
    /**
     * 合作企业id
     */
    private Integer partnershipId;
    /**
     * 单位名称
     */
    private String partnershipName;

    /**
     * 企业性质，1-国企，2-民企，3-外企
     */
    @NotNull
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
     * 负责人职务id
     */
    private Integer directorPositionId;

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
     * 联系人职务id
     */
    private Integer contactPositionId;

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
    private String amount;

    /**
     * 进账时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date paymentTime;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 合同开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date contractStartDate;

    /**
     * 合同结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date contractEndDate;
    
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
     * 对接服务人员
     */
    private String serviceProvider;

    /**
     * 是否上市 0-否。1-是
     */
    private Integer isListed;

    /**
     * 股票类型 1-A股票，2-港股，3-新三板，4-境外
     */
    private Integer stockTypeId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 当前活动id
     */
    //@NotNull
    private Integer operationId;

    /**
     * 子账号数量
     */
    //@NotNull
    private Integer subAccountNum;
}
