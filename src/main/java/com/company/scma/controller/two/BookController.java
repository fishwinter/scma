package com.company.scma.controller.two;

import com.company.scma.common.dto.two.CreateOrEditBookDto;
import com.company.scma.common.dto.two.GetArticleListDTO;
import com.company.scma.common.dto.two.GetBookListDTO;
import com.company.scma.common.dto.two.OperateItemDto;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.ArticleDetailVo;
import com.company.scma.common.vo.two.ArticleListItemVO;
import com.company.scma.common.vo.two.BookDetailVo;
import com.company.scma.common.vo.two.BookListItemVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图书接口
 */
@RestController
@RequestMapping(value = "/web/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    /**
     * 创建图书接口
     * @param createOrEditBookDto
     * @return
     */
    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public Result<String> createBook(@RequestBody CreateOrEditBookDto createOrEditBookDto){
        return null;
    }

    /**
     * 编辑图书接口
     * @param createOrEditBookDto
     * @return
     */
    @RequestMapping(value = "/editBook", method = RequestMethod.POST)
    public Result<String> editBook(@RequestBody CreateOrEditBookDto createOrEditBookDto){
        return null;
    }

    /**
     * 获取图书详情接口
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/getBookDetail", method = RequestMethod.GET)
    public Result<BookDetailVo> getBookDetail(@RequestParam("bookId") String bookId){
        return null;
    }

    /**
     * 获取图书列表接口
     * @param getBookListDTO
     * @return
     */
    @RequestMapping(value = "/getBookList", method = RequestMethod.POST)
    public Result<List<BookListItemVo>> getBookList(@RequestBody GetBookListDTO getBookListDTO){
        return null;
    }

    /**
     * 发布/撤回图书接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/publishBook", method = RequestMethod.POST)
    public Result<String> publishBook(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 图书上首页/撤回接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/homePageBook", method = RequestMethod.POST)
    public Result<String> homePageBook(@RequestBody OperateItemDto operateItemDto){
        return null;
    }

    /**
     * 删除图书
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public Result<String> deleteBook(@RequestParam("bookId") String bookId){
        return null;
    }
}
