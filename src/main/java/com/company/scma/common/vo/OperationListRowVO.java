package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OperationListRowVO {
    /**
     * 活动id
     */
    private Integer operationId;
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
     * 活动状态：0-未开始，1-正常，2-已结束
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date buildDate;
}
