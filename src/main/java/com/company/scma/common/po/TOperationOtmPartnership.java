package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_operation_otm_partnership
 * @author 
 */
@Data
@TableName("T_OPERATION_OTM_PARTNERSHIP")
public class TOperationOtmPartnership implements Serializable {
    /**
     * 序号
     */
    @TableId
    private Integer serialno;

    /**
     * 活动id
     */
    private Integer operationId;

    /**
     * 合作企业id
     */
    private Integer partnershipId;

    /**
     * 合作企业参加活动时间
     */
    private Date buildDate;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}