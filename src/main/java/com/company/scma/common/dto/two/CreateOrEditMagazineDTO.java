package com.company.scma.common.dto.two;

import lombok.Data;

/**
 * 创建杂志入参DTO
 */
@Data
public class CreateOrEditMagazineDTO {
    /**
     * 杂志id，编辑时传入
     */
    private String magazineId;

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
     * 所属年份
     */
    private String year;

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