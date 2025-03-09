package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class AdDetailVo {
    /**
     * 广告id
     */
    private String adId;

    /**
     * 标题
     */
    private String title;

    /**
     * 所属位置
     */
    private String linkPosition;

    /**
     * 跳转类型
     */
    private String linkType;

    /**
     * 外部连接地址
     */
    private String externalLink;

    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 条幅图片链接
     */
    private String imageLink;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;
}
