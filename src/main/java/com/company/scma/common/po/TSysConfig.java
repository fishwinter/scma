package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_sysconfig
 * @author 
 */
@Data
@TableName("T_SYSCONFIG")
public class TSysConfig implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer serialno;

    /**
     * 设置代码
     */
    private String custCode;

    /**
     * 设置值
     */
    private String custValue;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改者userid
     */
    private Integer modifyUserid;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}