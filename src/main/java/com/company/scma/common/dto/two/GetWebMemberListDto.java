package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class GetWebMemberListDto {
    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 联系人
     */
    private String linkPeople;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 注册时间开始
     */
    private String registerTimeStart;

    /**
     * 注册时间结束
     */
    private String registerTimeEnd;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页大小
     */
    private Integer pageSize;

}
