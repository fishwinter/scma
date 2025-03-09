package com.company.scma.controller.two;

import com.company.scma.common.dto.two.*;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.FLDetailVo;
import com.company.scma.common.vo.two.FLListItemVo;
import com.company.scma.common.vo.two.MagazineDetailVo;
import com.company.scma.common.vo.two.MagazineListItemVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 友情链接接口
 */
@RestController
@RequestMapping(value = "/web/friendshipLink", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendshipLinkController {
    /**
     * 新建友情链接接口
     * @param createOrEditFLDto
     * @return
     */
    @RequestMapping(value = "/createFL", method = RequestMethod.POST)
    public Result<String> createFL(@RequestBody CreateOrEditFLDto createOrEditFLDto){
        return null;
    }

    /**
     * 编辑友情链接接口
     * @param createOrEditFLDto
     * @return
     */
    @RequestMapping(value = "/editFL", method = RequestMethod.POST)
    public Result<String> createMagazine(@RequestBody CreateOrEditFLDto createOrEditFLDto){
        return null;
    }

    /**
     * 获取友情链接详情
     * @param fLId
     * @return
     */
    @RequestMapping(value = "/getFLDetail", method = RequestMethod.GET)
    public Result<FLDetailVo> getFLDetail(@RequestParam("fLId") String fLId){
        return null;
    }

    /**
     * 获取友情链接列表接口
     * @param getFLListDto
     * @return
     */
    @RequestMapping(value = "/getFLList", method = RequestMethod.POST)
    public Result<List<FLListItemVo>> getFLList(@RequestBody GetFLListDto getFLListDto){
        return null;
    }

    /**
     * 发布/撤回友情链接接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/publishFL", method = RequestMethod.POST)
    public Result<String> publishFL(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 删除友情链接
     * @param fLId
     * @return
     */
    @RequestMapping(value = "/deleteFL", method = RequestMethod.GET)
    public Result<String> deleteFL(@RequestParam("fLId") String fLId){
        return null;
    }
}
