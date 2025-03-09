package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class VideoDetailVo {
    /**
     * 视频id
     */
    private String videoId;

    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 标题
     */
    private String title;

    /**
     * 来源
     */
    private String source;

    /**
     * 发布时间
     */
    private String publishTime;

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
     * 展示位置顺序
     */
    private String displayOrder;

    /**
     * 外部连接地址
     */
    private String externalLink;

    /**
     * 内容摘要
     */
    private String contentSummary;

    /**
     * 视频文件链接
     */
    private String videoFileLink;

    /**
     * 封面图片链接
     */
    private String coverImageLink;
}
