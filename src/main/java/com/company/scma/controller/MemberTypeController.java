package com.company.scma.controller;

import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.MemberTypeBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "memberType", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberTypeController {
    @Autowired
    private MemberTypeBizService memberTypeBizService;

    @RequestMapping(value = "/getAllMemberType", method = RequestMethod.POST)
    @RequiresPermissions(value = {"member:add","sysConfig:visit"})
    public Result getAllMemberType(){
        return memberTypeBizService.getAllMemberType();
    }

    @RequestMapping(value = "/createMemberType", method = RequestMethod.POST)
    @RequiresPermissions("sysConfig:add")
    public Result createMemberType(@RequestBody List<String> memberTypeNameList){
        return memberTypeBizService.createMemberType(memberTypeNameList);
    }

    @RequestMapping(value = "/deleteMemberType", method = RequestMethod.POST)
    @RequiresPermissions("sysConfig:delete")
    public Result deleteMemberType(@RequestParam Integer memberTypeId){
        return memberTypeBizService.deleteMemberType(memberTypeId);
    }
}
