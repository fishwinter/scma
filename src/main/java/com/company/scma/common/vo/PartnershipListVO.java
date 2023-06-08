package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class PartnershipListVO {
    private Long partnershipTotal;
    private List<PartnershipListRowVO> partnershipListRowVOList;
}
