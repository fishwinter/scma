package com.company.scma.controller.two;

import com.company.scma.common.dto.two.*;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.AdDetailVo;
import com.company.scma.common.vo.two.AdListItemVo;
import com.company.scma.common.vo.two.MagazineDetailVo;
import com.company.scma.common.vo.two.MagazineListItemVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告接口
 */
@RestController
@RequestMapping(value = "/web/advertisement", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertisementController {

    /**
     * 创建广告接口
     * @param createOrEditAdDto
     * @return
     */
    @RequestMapping(value = "/creatAd", method = RequestMethod.POST)
    public Result<String> creatAd(@RequestBody CreateOrEditAdDto createOrEditAdDto){
        return null;
    }

    /**
     * 编辑广告接口
     * @param createOrEditAdDto
     * @return
     */
    @RequestMapping(value = "/editAd", method = RequestMethod.POST)
    public Result<String> editAd(@RequestBody CreateOrEditAdDto createOrEditAdDto){
        return null;
    }

    /**
     * 获取广告详情接口
     * @param adId
     * @return
     */
    @RequestMapping(value = "/getAdDetail", method = RequestMethod.GET)
    public Result<AdDetailVo> getAdDetail(@RequestParam("adId") String adId){
        return null;
    }

    /**
     * 获取广告列表接口
     * @param getAdListDto
     * @return
     */
    @RequestMapping(value = "/getAdList", method = RequestMethod.POST)
    public Result<List<AdListItemVo>> getAdList(@RequestBody GetAdListDto getAdListDto){
        return null;
    }

    /**
     * 发布/撤回广告接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/publishAd", method = RequestMethod.POST)
    public Result<String> publishAd(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 删除广告接口
     * @param adId
     * @return
     */
    @RequestMapping(value = "/deleteAd", method = RequestMethod.GET)
    public Result<String> deleteMagazine(@RequestParam("adId") String adId){
        return null;
    }
}
