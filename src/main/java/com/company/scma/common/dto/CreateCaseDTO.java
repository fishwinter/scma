package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CreateCaseDTO {
    /**
     * 标题
     */
    private String title;

    /**
     * 案列类别id
     */
    private Integer typeId;

    /**
     * 新建案例类别名称
     */
    private String newCaseType;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 摘要
     */
    private String caseAbstract;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 字数
     */
    private Integer numOfWords;

    /**
     * 备注
     */
    private String remark;
}
