package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateArticleDTO {
    /**
     * 作者id
     */
    @NotNull
    private Integer authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 刊期
     */
    private String frequency;

    /**
     * 编辑名称
     */
    private String editorName;

    /**
     * 文件url
     */
    private String fileUrl;
}
