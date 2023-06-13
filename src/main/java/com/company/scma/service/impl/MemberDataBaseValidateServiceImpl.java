package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.po.TMember;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.MemberDataBaseValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDataBaseValidateServiceImpl implements MemberDataBaseValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    @Autowired
    private MemberService memberService;
    @Override
    public Result validateGetMemberDataBaseDTO(GetMemberDataBaseDTO getMemberDataBaseDTO) {
        if(ObjectUtil.isEmpty(getMemberDataBaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(getMemberDataBaseDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        return Result.success();
    }

    @Override
    public Result validateAdoptMemberId(Integer memberId) {
        if(ObjectUtil.isEmpty(memberId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        TMember tMember = memberService.getMemberByMemberId(memberId);
        if(ObjectUtil.isEmpty(tMember) || ObjectUtil.isNotEmpty(tMember.getOwnerUserid())){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        return Result.success(tMember);
    }
}
