package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MemberListRowVO {
    private Integer memberId;
    private String memberName;
    private MemberTypeVO memberTypeVO;
    private String contacts;
    private String tel;
    private Date BuildDate;
}
