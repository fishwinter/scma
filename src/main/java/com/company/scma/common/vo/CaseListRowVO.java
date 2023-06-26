package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CaseListRowVO {
    /**
     * 案例id
     */
    private Integer caseId;

    /**
     * 案列类别
     */
    private CaseTypeVO caseTypeVO;

    /**
     * 标题
     */
    private String title;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 创建时间
     */
    private Date buildDate;
}
