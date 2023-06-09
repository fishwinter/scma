package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MemberListRowVO {
    /**
     * 会员id
     */
    private Integer memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 会员类型
     */
    private MemberTypeVO memberTypeVO;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 创建时间
     */
    private Date BuildDate;
}
