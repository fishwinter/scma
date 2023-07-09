package com.company.scma.common.vo;

import javafx.geometry.Pos;
import lombok.Data;

import java.util.List;

@Data
public class PartnershipConfigVO {
    //所有单位性质
    List<PartnershipTypeVO> partnershipTypeVOList;
    //所有合作项目
    List<PartnershipProjectTypeVO> partnershipProjectTypeVOList;
    //所有股票类型
    List<StockTypeVO> stockTypeVOList;
    //所有职务类型
    List<PositionVO> positionVOList;
}
