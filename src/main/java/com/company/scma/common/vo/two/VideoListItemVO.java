package com.company.scma.common.vo.two;

import lombok.Data;

/**
 * 视频项VO
 */
@Data
public class VideoListItemVO {
    /**
     * 总数
     */
    private String total;

    /**
     * id
     */
    private String videoId;

    /**
     * 标题
     */
    private String title;

    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;

    /**
     * 上首页，0-否，1-是
     */
    private String onHomepage;

    /**
     * 推荐置顶,0-否，1-是
     */
    private String recommendTop;

    /**
     * 作者
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