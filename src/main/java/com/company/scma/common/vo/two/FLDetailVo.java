package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class FLDetailVo {
    /**
     * 友情链接id
     */
    private String fLId;

    /**
     * 标题
     */
    private String title;

    /**
     * 展示位置顺序
     */
    private String displayOrder;

    /**
     * 外部连接地址
     */
    private String externalLink;

    /**
     * 链接图片链接
     */
    private String imageLink;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;
}
