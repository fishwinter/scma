package com.company.scma.controller.two;

import com.company.scma.common.dto.two.*;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 活动接口
 */
@RestController
@RequestMapping(value = "/web/activity", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController {

    /**
     * 创建活动接口
     * @param createOrEditActivityDTO
     * @return
     */
    @RequestMapping(value = "/createActivity", method = RequestMethod.POST)
    public Result<String> createActivity(@RequestBody CreateOrEditActivityDTO createOrEditActivityDTO){
        return null;
    }

    /**
     * 编辑活动接口
     * @param createOrEditActivityDTO
     * @return
     */
    @RequestMapping(value = "/editActivity", method = RequestMethod.POST)
    public Result<String> editActivity(@RequestBody CreateOrEditActivityDTO createOrEditActivityDTO){
        return null;
    }

    /**
     * 获取活动详情接口
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/getActivityDetail", method = RequestMethod.GET)
    public Result<ActivityDetailVo> getActivityDetail(@RequestParam("activityId") String activityId){
        return null;
    }

    /**
     * 获取活动列表接口
     * @param getActivityListDto
     * @return
     */
    @RequestMapping(value = "/getActivityList", method = RequestMethod.POST)
    public Result<List<ActivityListItemVo>> getColumnList(@RequestBody GetActivityListDto getActivityListDto){
        return null;
    }

    /**
     * 发布撤回活动接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/publishActivity", method = RequestMethod.POST)
    public Result<String> publishActivity(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 获取报名统计信息接口
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/getActivityCount", method = RequestMethod.GET)
    public Result<ActivityCountVo> getActivityCount(@RequestParam("activityId") String activityId){
        return null;
    }

    /**
     * 导出报名统计信息接口
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/exportActivityCount", method = RequestMethod.GET)
    public Result<String> exportActivityCount(@RequestParam("activityId") String activityId){
        return null;
    }


}
