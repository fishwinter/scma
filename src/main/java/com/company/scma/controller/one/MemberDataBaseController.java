package com.company.scma.controller.one;

import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.MemberDataBaseBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "memberDataBase", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberDataBaseController {
    @Autowired
    private MemberDataBaseBizService memberDataBaseBizService;

    @RequiresPermissions("memberDataBase:visit")
    @RequestMapping(value = "/getMyMemberDataBase", method = RequestMethod.POST)
    public Result getMyMemberDataBase(@RequestBody GetMemberDataBaseDTO getMemberDataBaseDTO){
        return memberDataBaseBizService.getMyMemberDataBase(getMemberDataBaseDTO);
    }

    @RequiresPermissions("member:add")
    @RequestMapping(value = "/adoptMember", method = RequestMethod.POST)
    public Result adoptMember(@RequestParam Integer memberId){
        return memberDataBaseBizService.adoptMember(memberId);
    }
}
