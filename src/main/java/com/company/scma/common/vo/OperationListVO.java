package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class OperationListVO {
    /**
     * 活动总数
     */
    private Long operationTotal;
    /**
     * 活动信息
     */
    private List<OperationListRowVO> operationListRowVOList;
}
