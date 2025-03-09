package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class FLListItemVo {
    /**
     * 友情链接id
     */
    private String fLId;

    /**
     * 标题
     */
    private String title;

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

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;
}
