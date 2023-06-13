package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.po.TMember;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.validateservice.MemberTypeValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberTypeValidateServiceImpl implements MemberTypeValidateService {
    @Autowired
    private MemberService memberService;
    @Override
    public Result validateDeleteMemberTypeId(Integer memberTypeId) {
        if(ObjectUtil.isEmpty(memberTypeId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //查询该类型绑定的会员
        List<TMember> tMemberList = memberService.getMemberByMemberType(memberTypeId);
        if(ObjectUtil.isNotEmpty(tMemberList)){
            return Result.getResult(ResultEnum.EXIST_MEMBER_TYPE_MEBER);
        }
        return Result.success();
    }
}
