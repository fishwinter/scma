package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysConfigDetailVO {
    private List<MemberTypeVO> memberTypeVOList;
    /**
     *活动过期后账号冻结时间
     */
    Integer suspendedTerm;
    /**
     *账号冻结后释放时间
     */
    Integer releaseTerm;
    /**
     *账号释放方式，1-释放到公司会员库，2-释放为公共资源
     */
    Integer releaseWay;
}
