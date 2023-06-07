package com.company.scma.controller;

import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.dto.EditMemberDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.MemberBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "member", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {
    @Autowired
    private MemberBizService memberBizService;

    @RequiresPermissions("member:visit")
    @RequestMapping(value = "/getMyMember", method = RequestMethod.POST)
    public Result getMyMember(@RequestBody GetMyMemberDTO getMyMemberDTO){
        return memberBizService.getMyMember(getMyMemberDTO);
    }

    @RequiresPermissions("member:visit")
    @RequestMapping(value = "/getMemberDetail", method = RequestMethod.POST)
    public Result getMemberDetail(@RequestParam Integer memberId){
        return memberBizService.getMemberDetail(memberId);
    }

    @RequiresPermissions("member:visit")
    @RequestMapping(value = "/checkMemberName", method = RequestMethod.POST)
    public Result checkMemberName(@RequestParam String memberName){
        return memberBizService.checkMemberName(memberName);
    }
    
    @RequestMapping(value = "/createMember", method = RequestMethod.POST)
    @RequiresPermissions("member:add")
    public Result createMember(@RequestBody CreateMemberDTO createMemberDTO){
        return memberBizService.createMember(createMemberDTO);
    }

    @RequestMapping(value = "/editMember", method = RequestMethod.POST)
    @RequiresPermissions("member:edit")
    public Result editMember(@RequestBody EditMemberDTO editMemberDTO){
        return memberBizService.editMember(editMemberDTO);
    }

    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    @RequiresPermissions("member:delete")
    public Result deleteMember(@RequestParam Integer memberId){
        return memberBizService.deleteMember(memberId);
    }
}
