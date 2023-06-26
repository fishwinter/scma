package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDetailVO {
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
     * 备注
     */
    private String remark;

    /**
     * 刊发文章
     */
    private List<ArticleListRowVO> articleListRowVOList;
}
