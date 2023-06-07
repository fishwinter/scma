package com.company.scma.controller;

import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.ItemBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
    @Autowired
    private ItemBizService itemBizService;

    @RequestMapping(value = "/getAllItem", method = RequestMethod.POST)
    @RequiresPermissions("member:add")
    public Result getAllItem(){
        return itemBizService.getAllItem();
    }
}
