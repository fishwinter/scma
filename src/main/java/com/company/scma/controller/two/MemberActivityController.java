package com.company.scma.controller.two;

import com.company.scma.common.dto.two.*;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.ActivityDetailVo;
import com.company.scma.common.vo.two.ActivityListItemVo;
import com.company.scma.common.vo.two.ParticipantsListItemVo;
import com.company.scma.common.vo.two.VideoListItemVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台-活动接口
 */
@RestController
@RequestMapping(value = "/web/memberActivity", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberActivityController {
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
     * 获取活动详情接口
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/getActivityDetail", method = RequestMethod.GET)
    public Result<ActivityDetailVo> getActivityDetail(@RequestParam("activityId") String activityId){
        return null;
    }


    /**
     * 新建参会人员
     * @param createOrEditParticipantsDto
     * @return
     */
    @RequestMapping(value = "/createParticipants", method = RequestMethod.POST)
    public Result<String> createParticipants(@RequestBody CreateOrEditParticipantsDto createOrEditParticipantsDto){
        return null;
    }

    /**
     * 编辑参会人员
     * @param createOrEditParticipantsDto
     * @return
     */
    @RequestMapping(value = "/editParticipants", method = RequestMethod.POST)
    public Result<String> editParticipants(@RequestBody CreateOrEditParticipantsDto createOrEditParticipantsDto){
        return null;
    }

    /**
     * 获取参会人员列表接口
     * @param getParticipantsListDto
     * @return
     */
    @RequestMapping(value = "/getParticipantsList", method = RequestMethod.POST)
    public Result<List<ParticipantsListItemVo>> getParticipantsList(@RequestBody GetParticipantsListDto getParticipantsListDto){
        return null;
    }

    /**
     * 删除参会人员
     * @param participantsId
     * @return
     */
    @RequestMapping(value = "/deleteParticipants", method = RequestMethod.GET)
    public Result<ActivityDetailVo> deleteParticipants(@RequestParam("participantsId") String participantsId){
        return null;
    }

    
}
