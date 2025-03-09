package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class GetFLListDto {
    /**
     * 标题
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
