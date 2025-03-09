package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class ActivityListItemVo {
    /**
     * 总数
     */
    private String total;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动负责人
     */
    private String activityLeader;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 报名时间（开始时间）
     */
    private String registrationStartTime;

    /**
     * 报名时间（结束时间）
     */
    private String registrationEndTime;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;
}
