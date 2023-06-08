package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class MemberDataBaseListVO {
    private Long memberDataBaseTotal;
    private List<MemberDataBaseListRowVO> memberDataBaseListRowVOList;
}
