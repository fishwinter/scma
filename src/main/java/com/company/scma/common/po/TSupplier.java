package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_supplier
 * @author 
 */
@Data
@TableName("T_SUPPLIER")
public class TSupplier implements Serializable {
    /**
     * 供应商id
     */
    @TableId
    private Integer supplierId;

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
//    private Integer memberTypeId;

    /**
     * 单位类型id
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

//    /**
//     * 负责人职务
//     */
//    private String directorPosition;

    /**
     * 负责人职务id
     */
    private Integer directorPositionId;

    /**
     * 企业logo url
     */
    private String logoUrl;

    /**
     * 联系人职务id
     */
    private Integer contactPositionId;

    /**
     * 是否参加过物博会，0-否，1-是
     */
    private Integer joinLogisticsFair;

    /**
     * 参加物博会年份id,多个同时存在时使用逗号隔开
     */
    private String joinYearId;

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
     * 合作项目，1-协办，2-广告，3-活动，多个同时存在时使用逗号隔开
     */
    private String projectType;

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

    /**
     * 创建用户userid
     */
    private Integer buildUserid;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 修改用户userid
     */
    private Integer modifyUserid;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}