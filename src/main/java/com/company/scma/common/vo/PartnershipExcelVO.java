package com.company.scma.common.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class PartnershipExcelVO {
    /**
     * 单位名称
     */
    @ExcelProperty("单位名称")
    private String partnershipName;

    /**
     * 企业性质，1-国企，2-民企，3-外企
     */
    @ExcelProperty("单位性质")
    private String partnershipTypeName;

    /**
     * 地址
     */
    @ExcelProperty("联系地址")
    private String addr;

    /**
     * 企业邮箱
     */
    @ExcelProperty("邮箱")
    private String email;

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
     * 负责人姓名
     */
    @ExcelProperty("负责人姓名")
    private String directorName;

    /**
     * 负责人职务id
     */
    @ExcelProperty("负责人职务")
    private String directorPosition;

    /**
     * 负责人手机号
     */
    @ExcelProperty("负责人手机号")
    private String directorTel;

    /**
     * 负责人微信号
     */
    @ExcelProperty("负责人微信号")
    private String directorWechat;

    /**
     * 联系人姓名
     */
    @ExcelProperty("联系人")
    private String contactName;

    /**
     * 联系人职务
     */
    @ExcelProperty("联系人职务")
    private String contactPosition;

    /**
     * 联系人手机
     */
    @ExcelProperty("联系电话")
    private String contactTel;

    /**
     * 联系人微信号
     */
    @ExcelProperty("联系人微信号")
    private String contactWechat;

    /**
     * 合作金额
     */
    @ExcelProperty("合作金额")
    private String amount;

    /**
     * 进账时间
     */
    @ExcelProperty("进账时间")
    private String paymentTime;

    /**
     * 合同编号
     */
    @ExcelProperty("合同编号")
    private String contractNumber;

    /**
     * 合同开始时间
     */
    @ExcelProperty("合同开始时间")
    private String contractStartDate;

    /**
     * 合同结束时间n
     */
    @ExcelProperty("合同结束时间")
    private String contractEndDate;

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
     * 对接服务人员
     */
    @ExcelProperty("对接服务人员")
    private String serviceProvider;

    /**
     * 是否上市 0-否。1-是
     */
    @ExcelProperty("是否上市")
    private String isListed;

    /**
     * 股票类型 1-A股票，2-港股，3-新三板，4-境外
     */
    @ExcelProperty("股票类型")
    private String stockType;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;
}
