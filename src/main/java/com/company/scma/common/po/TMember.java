package com.company.scma.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * t_memeber
 * @author 
 */
@Data
@TableName("T_MEMBER")
public class TMember implements Serializable {
    /**
     * 会员id
     */
    @TableId
    private Integer memberId;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 会员类型id
     */
    private Integer memberTypeId;

    /**
     * 法人
     */
    private String corporation;

    /**
     * 注册地址
     */
    private String regAddress;

    /**
     * 创建日期
     */
    private Date steupdate;

    /**
     * 统一社会信用代码
     */
    private String creditno;

    /**
     * 注册资本
     */
    private String regstock;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人职务
     */
    private String contactTitle;

    /**
     * 联系人电话
     */
    private String contactTel;

    /**
     * 联系人地址
     */
    private String contactAddr;

    private String provinceCode;

    private String cityCode;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 创建人userid
     */
    private Integer buildUserid;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改人userid
     */
    private Integer modifyUserid;

    /**
     * 所属用户id
     */
    private Integer ownerUserid;

    /**
     * 所属用户名称
     */
    private String ownerUsername;

    /**
     * 所属合作企业id
     */
    private Integer ownerPartnershipId;

    /**
     * 所属合作企业名称
     */
    private String ownerPartnershipName;

    /**
     * 状态：0-已释放为公共资源，1-正常，2-已释放到公司会员数据库
     */
    private Integer status;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}