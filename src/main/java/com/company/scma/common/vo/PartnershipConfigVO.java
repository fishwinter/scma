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
    //所有负责人职务类型
    List<PositionVO> directorPositionVOList;
    //所有联系人职务类型
    List<PositionVO> contactPositionVOList;
    //所有单位类型
    List<EnterpriseTypeVO> enterpriseTypeVOList;
    //所有参与物博会年份
    List<YearVO> yearVOList;
}
