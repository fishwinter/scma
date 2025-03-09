package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class ActivityCountVo {
    /**
     * 活动id
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 已报名人数
     */
    private String registerNum;

    /**
     * 审核通过人数
     */
    private String verifyNum;

    /**
     * 已缴费人数
     */
    private String payNum;


}
