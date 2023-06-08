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
     * 负责人姓名
     */
    private String directorName;

    /**
     * 负责人手机号
     */
    private String directorTel;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date buildDate;
}
