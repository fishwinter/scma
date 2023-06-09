package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class PartnershipListVO {
    /**
     * 合作企业总数
     */
    private Long partnershipTotal;
    /**
     * 合作企业信息
     */
    private List<PartnershipListRowVO> partnershipListRowVOList;
}
