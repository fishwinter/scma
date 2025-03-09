package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class GetAdListDto {
    /**
     * 标题
     */
    private String title;

    /**
     * 所属位置
     */
    private String linkPosition;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
