package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PartnershipListRowVO {
    /**
     * 单位名称
     */
    private String partnershipName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机
     */
    private String contactTel;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date buildDate;
}
