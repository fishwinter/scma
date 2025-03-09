package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class BookListItemVo {
    /**
     * 总数
     */
    private String total;

    /**
     * 标题
     */
    private String title;

    /**
     * 上首页，0-否，1-是
     */
    private String onHomepage;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;

    /**
     * 发布者
     */
    private String author;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 浏览量
     */
    private String viewCount;
}
