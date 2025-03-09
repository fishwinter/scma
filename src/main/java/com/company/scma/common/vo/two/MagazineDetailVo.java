package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class MagazineDetailVo {
    /**
     * 杂志id
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
     * 是否发布，0-否，1-是
     */
    private String isPublish;

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
}
