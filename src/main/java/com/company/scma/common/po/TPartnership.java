package com.company.scma.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * t_partnership
 * @author 
 */
@Data
public class TPartnership implements Serializable {
    /**
     * 合作企业id
     */
    @TableId
    private Integer partnershipId;

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
    private String amount;

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
     * 合作项目，1-协办，2-广告，3-活动，多个同时存在时使用逗号隔开
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
    private Integer operationId;

    /**
     * 当前绑定活动状态：0-未开始，1-正常，2-已结束
     */
    private Integer operationStatus;

    /**
     * 管理员userid
     */
    private Integer managerUserid;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 创建者userid
     */
    private Integer buildUserid;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改者userid
     */
    private Integer modifyUserid;

    /**
     * 删除标值，0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}