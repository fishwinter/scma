package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_operation
 * @author 
 */
@Data
@TableName("T_OPERATION")
public class TOperation implements Serializable {
    /**
     * 活动id
     */
    @TableId
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

    /**
     * 活动状态：0-未开始，1-正常，2-已结束
     */
    private Integer status;

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
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}