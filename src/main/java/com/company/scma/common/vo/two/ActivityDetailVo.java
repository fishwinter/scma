package com.company.scma.common.vo.two;

import com.company.scma.common.dto.two.ActivityScheduleDto;
import com.company.scma.common.dto.two.HotelArrangementDto;
import lombok.Data;

import java.util.List;

@Data
public class ActivityDetailVo {
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
     * 活动时间（开始时间）
     */
    private String activityStartTime;

    /**
     * 活动时间（结束时间）
     */
    private String activityEndTime;

    /**
     * 活动日程安排
     */
    private List<ActivityScheduleDto> activitySchedules;

    /**
     * 宣传海报链接
     */
    private String promotionPosterLink;

    /**
     * 活动介绍，html格式
     */
    private String activityIntroduction;

    /**
     * 报名时间（开始时间）
     */
    private String registrationStartTime;

    /**
     * 报名时间（结束时间）
     */
    private String registrationEndTime;

    /**
     * 报名费用
     */
    private String registrationFee;

    /**
     * 酒店安排
     */
    private List<HotelArrangementDto> hotelArrangements;

    /**
     * 是否发布，0-否，1-是
     */
    private String isPublish;
}
