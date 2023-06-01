package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_role
 * @author 
 */
@Data
@TableName("T_ROLE")
public class TRole implements Serializable {
    /**
     * 角色id
     */
    @TableId
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色介绍
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