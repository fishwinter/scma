package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_author
 * @author 
 */
@Data
@TableName("T_AUTHOR")
public class TAuthor implements Serializable {
    /**
     * 作者id
     */
    @TableId
    private Integer authorId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 职务
     */
    private String position;

    /**
     * 微信号
     */
    private String wechatAccount;

    /**
     * 地址
     */
    private String addr;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 创建用户id
     */
    private Integer buildUserid;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改用户id
     */
    private Integer modifyUserid;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}