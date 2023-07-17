package com.company.scma.common.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class AuthorExcelVO {
    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 电话
     */
    @ExcelProperty("手机号")
    private String tel;

    /**
     * 工作单位
     */
    @ExcelProperty("工作单位")
    private String workUnit;

    /**
     * 职务
     */
    @ExcelProperty("职务")
    private String position;

    /**
     * 微信号
     */
    @ExcelProperty("微信号")
    private String wechatAccount;

    /**
     * 地址
     */
    @ExcelProperty("联系地址")
    private String addr;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String mail;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;
}
