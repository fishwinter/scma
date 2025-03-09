package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class WebMemberListItemVo {
    /**
     * 会员id
     */
    private String memberId;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 单位名称
     */
    private String organName;

    /**
     * 联系人
     */
    private String linkPeople;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 注册时间
     */
    private String registerTime;

    /**
     * 状态，0-禁用，1-启用
     */
    private String status;
}
