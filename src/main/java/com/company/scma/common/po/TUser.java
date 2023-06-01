package com.company.scma.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user
 * @author 
 */
@Data
@TableName("T_USER")
public class TUser implements Serializable {
    /**
     * 用户id
     */
    @TableId
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别，1-男，2-女
     */
    private Integer sex;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String addr;

    /**
     * 头像上传url
     */
    private String imageUrl;

    /**
     * 类型：1-普通用户，2-子账号用户
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 创建该用户的用户的userid
     */
    private Integer buildUserid;

    /**
     * 创建该用户的合作企业id
     */
    private Integer buildPartnershipid;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改者的userid
     */
    private Integer modifyUserid;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 用户状态：0-失效，1-有效
     */
    private Integer status;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}