package com.company.scma.common.vo;

import lombok.Data;

@Data
public class ArticleListRowVO {
    /**
     * 文章id
     */
    private Integer articleId;

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
