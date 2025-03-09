package com.company.scma.common.vo.two;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ColumnDetailVo {

    /**
     * 栏目id，编辑栏目时传入
     */
    private String columnId;

    /**
     * 上级栏目 ID
     */
    private String parentId;

    /**
     * 上级栏目名称
     */
    private String parentName;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 是否展示, 0-不展示，1-展示
     */
    private String isDisplay;

    /**
     * 展示位置顺序
     */
    private String displayOrder;

    /**
     * 外部链接地址
     */
    private String externalLink;

    /**
     * 备注说明
     */
    private String remarks;
}
