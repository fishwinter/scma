package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class CreateOrEditBookDto {
    /**
     * 图书id，编辑图书时传入
     */
    private String bookId;

    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 上首页，0-否，1-是
     */
    private String onHomepage;

    /**
     * 展示位置顺序
     */
    private String displayOrder;

    /**
     * 实体销售链接
     */
    private String sealLink;

    /**
     * 内容摘要
     */
    private String contentSummary;

    /**
     * 封面图片链接
     */
    private String coverImageLink;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;
}
