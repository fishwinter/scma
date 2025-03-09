package com.company.scma.common.dto.two;

import lombok.Data;

/**
 * 活动日程安排对象
 */
@Data
public class ActivityScheduleDto {
    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 地点
     */
    private String location;

    /**
     * 简介
     */
    private String introduction;
}