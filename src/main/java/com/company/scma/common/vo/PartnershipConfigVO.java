package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class PartnershipConfigVO {
    List<PartnershipTypeVO> partnershipTypeVOList;
    List<PartnershipProjectTypeVO> partnershipProjectTypeVOList;
}
