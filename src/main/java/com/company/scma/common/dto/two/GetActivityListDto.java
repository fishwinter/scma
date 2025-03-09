package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class GetActivityListDto {
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
     * 活动时间（开始时间）
     */
    private String activityStartTime;

    /**
     * 活动时间（结束时间）
     */
    private String activityEndTime;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
