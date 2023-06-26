package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class CaseDetailVO {
    /**
     * 标题
     */
    private String title;

    /**
     * 案列类别
     */
    private CaseTypeVO myCaseType;

    /**
     * 所有案列类别
     */
    private List<CaseTypeVO> allCaseType;
    
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
