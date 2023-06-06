package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * t_member_type
 * @author 
 */
@Data
public class TMemberType implements Serializable {

    /**
     * 会员类型id
     */
    @TableId
    private Integer memberTypeId;

    /**
     * 会员类型名称
     */
    private String memberTypeName;

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