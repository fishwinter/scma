package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class MemberListVO {
    /**
     * 会员总数
     */
    private Long memberTotal;
    /**
     * 会员信息
     */
    private List<MemberListRowVO> memberListRowVOList; 
}
