package com.company.scma.common.dto.two;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 栏目创建/编辑接口入参 DTO
 */
@Data
public class CreateOrEditColumnDto {

    /**
     * 栏目id，编辑栏目时传入
     */
    private String columnId;

    /**
     * 上级栏目 ID
     */
    private String parentId;

    /**
     * 栏目名称（必填）
     */
    @NotBlank(message = "栏目名称不能为空")
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