package com.company.scma.controller.two;

import com.company.scma.common.dto.two.CreateOrEditVideoDto;
import com.company.scma.common.dto.two.GetMagazineListDTO;
import com.company.scma.common.dto.two.GetVideoListDTO;
import com.company.scma.common.dto.two.OperateItemDto;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.MagazineListItemVo;
import com.company.scma.common.vo.two.VideoDetailVo;
import com.company.scma.common.vo.two.VideoListItemVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 视频接口
 */
@RestController
@RequestMapping(value = "/web/video", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoController {
    /**
     * 创建视频接口
     * @param createOrEditVideoDto
     * @return
     */
    @RequestMapping(value = "/createVideo", method = RequestMethod.POST)
    public Result<String> createVideo(@RequestBody CreateOrEditVideoDto createOrEditVideoDto){
        return null;
    }

    /**
     * 编辑视频接口
     * @param createOrEditVideoDto
     * @return
     */
    @RequestMapping(value = "/editVideo", method = RequestMethod.POST)
    public Result<String> editVideo(@RequestBody CreateOrEditVideoDto createOrEditVideoDto){
        return null;
    }

    /**
     * 获取视频详情接口
     * @param videoId
     * @return
     */
    @RequestMapping(value = "/getVideoDetail", method = RequestMethod.GET)
    public Result<VideoDetailVo> getVideoDetail(@RequestParam("videoId") String videoId){
        return null;
    }

    /**
     * 获取视频列表
     * @param getVideoListDTO
     * @return
     */
    @RequestMapping(value = "/getVideoList", method = RequestMethod.POST)
    public Result<List<VideoListItemVO>> getVideoList(@RequestBody GetVideoListDTO getVideoListDTO){
        return null;
    }

    /**
     * 发布/撤回视频接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/publishVideo", method = RequestMethod.POST)
    public Result<String> publishVideo(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 视频上首页/撤回接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/homePageVideo", method = RequestMethod.POST)
    public Result<String> homePageVideo(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 视频置顶/撤回接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/topUpVideo", method = RequestMethod.POST)
    public Result<String> topUpVideo(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 删除视频接口
     * @param videoId
     * @return
     */
    @RequestMapping(value = "/deleteVideo", method = RequestMethod.GET)
    public Result<String> deleteVideo(@RequestParam("videoId") String videoId){
        return null;
    }

}
