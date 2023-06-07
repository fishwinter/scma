package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class OperationListVO {
    private Long operationTotal;
    private List<OperationListRowVO> operationListRowVOList;
}
