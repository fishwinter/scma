package com.company.scma.common.dto.two;

import lombok.Data;

/**
 * 查询文章列表入参DTO
 */
@Data
public class GetArticleListDTO {

    /**
     * 文章标题
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
     * 文章状态，0-未发布，1-已发布
     */
    private String status;

    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页大小
     */
    private Integer pageSize;
}