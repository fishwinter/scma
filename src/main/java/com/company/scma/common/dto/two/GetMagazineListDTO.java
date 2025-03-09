package com.company.scma.common.dto.two;

import lombok.Data;

/**
 * 查询杂志列表入参DTO
 */
@Data
public class GetMagazineListDTO {

    /**
     * 杂志标题
     */
    private String title;

    /**
     * 发布时间开始
     */
    private String publishTimeStart;

    /**
     * 发布时间结束
     */
    private String publishTimeEnd;

    /**
     * 图书状态，0-未发布，1-已发布
     */
    private String status;

    /**
     * 是否有电子刊，0-否，1-是
     */
    private String isElectronic;

    /**
     * 所属年份
     */
    private String year;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页大小
     */
    private Integer pageSize;
}