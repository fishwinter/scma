package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDataBaseListRowVO {
    private Integer memberId;
    private String memberName;
    private MemberTypeVO memberTypeVO;
    private String partnershipName;
    private String ownerUsername;
    private Date BuildDate;
}
