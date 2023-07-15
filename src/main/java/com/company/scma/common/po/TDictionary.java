package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_dictionary
 * @author 
 */
@Data
@TableName("T_DICTIONARY")
public class TDictionary implements Serializable {
    /**
     * 字典数据id
     */
    @TableId
    private Integer dicId;

    /**
     * 字典数据名称
     */
    private String dicName;

    /**
     * 字典类型
     */
    private Integer dicType;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 创建者userid
     */
    private Integer buildUserid;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}