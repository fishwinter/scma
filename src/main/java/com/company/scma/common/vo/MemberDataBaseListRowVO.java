package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDataBaseListRowVO {
    /**
     * 会员id
     */
    private Integer memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 类型
     */
    private MemberTypeVO memberTypeVO;
    /**
     * 所属单位
     */
    private String partnershipName;
    /**
     * 所属人
     */
    private String ownerUsername;
    /**
     * 创建时间
     */
    private Date BuildDate;
}
