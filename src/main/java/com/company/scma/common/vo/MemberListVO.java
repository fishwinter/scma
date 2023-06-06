package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class MemberListVO {
    private Long memberTotal;
    private List<MemberListRowVO> memberListRowVOList; 
}
