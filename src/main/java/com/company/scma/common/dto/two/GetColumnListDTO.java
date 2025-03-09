package com.company.scma.common.dto.two;

import lombok.Data;

/**
 * 查询栏目列表入参DTO
 */
@Data
public class GetColumnListDTO {

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 创建时间开始,yyyy-mm-dd hh:mm:ss格式
     */
    private String createTimeStart;

    /**
     * 创建时间结束，yyyy-mm-dd hh:mm:ss格式
     */
    private String createTimeEnd;

    /**
     * 是否展示，o-否，1-是
     */
    private String isDisplay;

    /**
     * 是否删除，o-否，1-是
     */
    private String isDeleted;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页大小
     */
    private Integer pageSize;
}