package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class CaseListVO {
    /**
     * 案例信息总数
     */
    private Long caseTotal;
    /**
     * 案例信息列表
     */
    private List<CaseListRowVO> caseListRowVOList;
}
