package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OperationDetailVO {
    /**
     * 活动名称
     */
    private String operationName;

    /**
     * 活动负责人
     */
    private String director;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 活动开始时间
     */
    private Date startDate;

    /**
     * 活动结束时间
     */
    private Date endDate;

    /**
     * 简介
     */
    private String introduction;
}
