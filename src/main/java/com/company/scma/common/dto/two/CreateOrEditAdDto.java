package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class CreateOrEditAdDto {
    /**
     * 广告id，编辑广告时传入
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
     * 站内栏目id
     */
    private String columnId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 条幅图片链接
     */
    private String imageLink;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;
}
