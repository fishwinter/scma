package com.company.scma.common.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class SupplierExcelVO {
    /**
     * 供应商名称
     */
    @ExcelProperty("单位名称")
    private String supplierName;

    /**
     * 联系人
     */
    @ExcelProperty("联系人")
    private String contacts;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    private String contactTel;

    /**
     * 联系地址
     */
    @ExcelProperty("联系地址")
    private String contactAddr;

    /**
     * 简介
     */
    @ExcelProperty("简介")
    private String introduction;

    /**
     * 单位类型
     */
    @ExcelProperty("单位类型")
    private String enterpriseType;

    /**
     * 单位性质
     */
    @ExcelProperty("单位性质")
    private String partnershipTypeName;

    /**
     * 微信公众号
     */
    @ExcelProperty("微信公众号")
    private String wechatAccount;

    /**
     * 视频号
     */
    @ExcelProperty("视频号")
    private String videoAccount;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;

    /**
     * 负责人姓名
     */
    @ExcelProperty("负责人姓名")
    private String directorName;
    

    /**
     * 负责人职务
     */
    @ExcelProperty("负责人职务")
    private String directorPosition;

    /**
     * 联系人职务
     */
    @ExcelProperty("联系人职务")
    private String contactPosition;

    /**
     * 是否参加过物博会，0-否，1-是
     */
    @ExcelProperty("是否参加过物博会")
    private String joinLogisticsFair;

    /**
     * 服务对接人
     */
    @ExcelProperty("服务对接人")
    private String serviceProvider;

    /**
     * 负责人手机号
     */
    @ExcelProperty("负责人手机号")
    private String directorTel;

    /**
     * 合作案例
     */
    @ExcelProperty("合作案例")
    private String cooperationCases;

    /**
     * 合作项目，1-协办，2-广告，3-活动，多个同时存在时使用逗号隔开
     */
    @ExcelProperty("合作项目")
    private String projectType;

    /**
     * 首次加入协办单位时间
     */
    @ExcelProperty("首次加入协办单位时间")
    private String firstJoinTime;

    /**
     * 会长名称
     */
    @ExcelProperty("会长")
    private String presidentName;

    /**
     * 会长电话
     */
    @ExcelProperty("会长电话")
    private String presidentTel;

    /**
     * 秘书长名称
     */
    @ExcelProperty("秘书长")
    private String secretaryName;

    /**
     * 秘书长电话
     */
    @ExcelProperty("秘书长电话")
    private String secretaryTel;
}
