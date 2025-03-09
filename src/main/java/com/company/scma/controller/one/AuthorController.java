package com.company.scma.controller.one;

import com.company.scma.common.dto.CreateAuthorDTO;
import com.company.scma.common.dto.EditAuthorDTO;
import com.company.scma.common.dto.GetAuthorDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.AuthorBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "author", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    
    @Autowired
    private AuthorBizService authorBizService;

    @RequestMapping(value = "/getAllAuthor", method = RequestMethod.POST)
    @RequiresPermissions("author:visit")
    public Result getAllAuthor(@RequestBody GetAuthorDTO getAuthorDTO){
        return authorBizService.getAllAuthor(getAuthorDTO);
    }

    @RequestMapping(value = "/getAuthorDetail", method = RequestMethod.POST)
    @RequiresPermissions("author:visit")
    public Result getAuthorDetail(@RequestParam Integer authorId){
        return authorBizService.getAuthorDetail(authorId);
    }
    
    @RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
    @RequiresPermissions("author:add")
    public Result createAuthor(@RequestBody CreateAuthorDTO createAuthorDTO){
        return authorBizService.createAuthor(createAuthorDTO);
    }

    @RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
    @RequiresPermissions("author:edit")
    public Result editAuthor(@RequestBody EditAuthorDTO editAuthorDTO){
        return authorBizService.editAuthor(editAuthorDTO);
    }

    @RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST)
    @RequiresPermissions("author:delete")
    public Result deleteAuthor(@RequestParam Integer authorId){
        return authorBizService.deleteAuthor(authorId);
    }
}
