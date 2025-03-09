package com.company.scma.controller.two;

import com.company.scma.common.dto.CreateArticleDTO;
import com.company.scma.common.dto.two.CreateOrEditColumnDto;
import com.company.scma.common.dto.two.GetColumnListDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.ColumnDetailVo;
import com.company.scma.common.vo.two.ColumnListItemVO;
import com.company.scma.common.vo.two.ColumnTreeItemVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 栏目接口
 */
@RestController
@RequestMapping(value = "/web/column", produces = MediaType.APPLICATION_JSON_VALUE)
public class ColumnController {

    /**
     * 新建栏目接口
     * @param createOrEditColumnDto
     * @return
     */
    @RequestMapping(value = "/createColumn", method = RequestMethod.POST)
    public Result<String> createColumn(@RequestBody CreateOrEditColumnDto createOrEditColumnDto){
        return null;
    }

    /**
     * 编辑栏目接口
     * @param createOrEditColumnDto
     * @return
     */
    @RequestMapping(value = "/editColumn", method = RequestMethod.POST)
    public Result<String> editColumn(@RequestBody CreateOrEditColumnDto createOrEditColumnDto){
        return null;
    }


    /**
     * 获取栏目详情接口
     * @param columnId
     * @return
     */
    @RequestMapping(value = "/getColumnDetail", method = RequestMethod.GET)
    public Result<ColumnDetailVo> getColumnDetail(@RequestParam("columnId") String columnId){
        return null;
    }

    /**
     * 获取栏目列表页接口
     * @param getColumnListDTO
     * @return
     */
    @RequestMapping(value = "/getColumnList", method = RequestMethod.POST)
    public Result<List<ColumnListItemVO>> getColumnList(@RequestBody GetColumnListDTO getColumnListDTO){
        return null;
    }

    /**
     * 删除栏目接口
     * @param columnId
     * @return
     */
    @RequestMapping(value = "/deleteColumn", method = RequestMethod.GET)
    public Result<String> deleteColumn(@RequestParam("columnId") String columnId){
        return null;
    }

    /**
     * 获取栏目树接口
     * @return
     */
    @RequestMapping(value = "/getColumnTree", method = RequestMethod.GET)
    public Result<ColumnTreeItemVo> getColumnTree(){
        return null;
    }

}
