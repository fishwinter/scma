package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class SupplierListVO {
    private Long supplierTotal;
    private List<SupplierListRowVO> supplierListRowVOList;
}
