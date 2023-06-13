package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TUser;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.MemberDataBaseListVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.MemberDataBaseBizService;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.validateservice.MemberDataBaseValidateService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemberDataBaseBizServiceImpl implements MemberDataBaseBizService {
    @Autowired
    private MemberDataBaseValidateService memberDataBaseValidateService;
    @Autowired
    private MemberService memberService;
    @Override
    public Result getMyMemberDataBase(GetMemberDataBaseDTO getMemberDataBaseDTO) {
        //参数校验
        Result result = memberDataBaseValidateService.validateGetMemberDataBaseDTO(getMemberDataBaseDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TMember> tMemberIPage = memberService.getMemberByCondition(getMemberDataBaseDTO);
        //数据封装
        MemberDataBaseListVO memberDataBaseListVO = GenerateUtil.getMemberDataBaseListVO(tMemberIPage);
        //查询所有合作企业
        
        //查询所有用户
        
        //返回
        return null;
    }

    @Override
    public Result adoptMember(Integer memberId) {
        //参数校验
        Result result = memberDataBaseValidateService.validateAdoptMemberId(memberId);
        if(!Result.isSuccess(result)){
            return result;
        }
        TMember tMember = (TMember) result.getData();
        //认领
        TUser currentUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        tMember.setOwnerUserid(currentUser.getUserid());
        tMember.setModifyDate(new Date());
        tMember.setMemberId(currentUser.getUserid());
        //返回
        return Result.success();
    }
}
