package com.company.scma.controller.two;

import com.company.scma.common.dto.two.*;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章接口
 */
@RestController
@RequestMapping(value = "/web/article", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {
    /**
     * 创建文章接口
     * @param createOrEditArticleDTO
     * @return
     */
    @RequestMapping(value = "/createArticle", method = RequestMethod.POST)
    public Result<String> createArticle(@RequestBody CreateOrEditArticleDTO createOrEditArticleDTO){
        return null;
    }

    /**
     * 编辑文章接口
     * @param createOrEditArticleDTO
     * @return
     */
    @RequestMapping(value = "/editArticle", method = RequestMethod.POST)
    public Result<String> editArticle(@RequestBody CreateOrEditArticleDTO createOrEditArticleDTO){
        return null;
    }

    /**
     * 获取文章详情接口
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/getArticleDetail", method = RequestMethod.GET)
    public Result<ArticleDetailVo> getArticleDetail(@RequestParam("articleId") String articleId){
        return null;
    }

    /**
     * 获取文章列表接口
     * @param getArticleListDTO
     * @return
     */
    @RequestMapping(value = "/getArticleList", method = RequestMethod.POST)
    public Result<List<ArticleListItemVO>> getColumnList(@RequestBody GetArticleListDTO getArticleListDTO){
        return null;
    }

    /**
     * 发布/撤回文章接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/publishArticle", method = RequestMethod.POST)
    public Result<String> publishArticle(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 文章上首页/撤回接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/homePageArticle", method = RequestMethod.POST)
    public Result<String> homePageArticle(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 文章置顶/撤回接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/topUpArticle", method = RequestMethod.POST)
    public Result<String> topUpArticle(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 删除文章接口
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
    public Result<String> deleteArticle(@RequestParam("articleId") String articleId){
        return null;
    }
}
