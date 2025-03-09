package com.company.scma.controller.two;

import com.company.scma.common.dto.two.*;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.ArticleDetailVo;
import com.company.scma.common.vo.two.BookListItemVo;
import com.company.scma.common.vo.two.MagazineDetailVo;
import com.company.scma.common.vo.two.MagazineListItemVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 杂志接口
 */
@RestController
@RequestMapping(value = "/web/magazine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MagazineController {

    /**
     * 创建杂志接口
     * @param createOrEditMagazineDTO
     * @return
     */
    @RequestMapping(value = "/createMagazine", method = RequestMethod.POST)
    public Result<String> createMagazine(@RequestBody CreateOrEditMagazineDTO createOrEditMagazineDTO){
        return null;
    }

    /**
     * 编辑杂志接口
     * @param createOrEditMagazineDTO
     * @return
     */
    @RequestMapping(value = "/editMagazine", method = RequestMethod.POST)
    public Result<String> editMagazine(@RequestBody CreateOrEditMagazineDTO createOrEditMagazineDTO){
        return null;
    }

    /**
     * 获取杂志详情接口
     * @param magazineId
     * @return
     */
    @RequestMapping(value = "/getMagazineDetail", method = RequestMethod.GET)
    public Result<MagazineDetailVo> getMagazineDetail(@RequestParam("magazineId") String magazineId){
        return null;
    }

    /**
     * 获取杂志列表接口
     * @param getMagazineListDTO
     * @return
     */
    @RequestMapping(value = "/getMagazineList", method = RequestMethod.POST)
    public Result<List<MagazineListItemVo>> getMagazineList(@RequestBody GetMagazineListDTO getMagazineListDTO){
        return null;
    }

    /**
     * 发布/撤回杂志接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/publishMagazine", method = RequestMethod.POST)
    public Result<String> publishMagazine(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 图书上首页/撤回接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/homePageMagazine", method = RequestMethod.POST)
    public Result<String> homePageMagazine(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 删除杂志接口
     * @param magazineId
     * @return
     */
    @RequestMapping(value = "/deleteMagazine", method = RequestMethod.GET)
    public Result<String> deleteMagazine(@RequestParam("magazineId") String magazineId){
        return null;
    }
}
