package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class MemberDataBaseListVO {
    /**
     * 会员总数
     */
    private Long memberDataBaseTotal;
    /**
     * 会员信息
     */
    private List<MemberDataBaseListRowVO> memberDataBaseListRowVOList;
}
