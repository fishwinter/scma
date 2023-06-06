package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.dto.EditMemberDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TMemberType;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.MemberListVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.MemberBizService;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.mapperservice.MemberTypeService;
import com.company.scma.service.validateservice.MemberValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberBizServiceImpl implements MemberBizService {
    @Autowired
    private MemberValidateService memberValidateService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberTypeService memberTypeService;

    @Override
    public Result getMyMember(GetMyMemberDTO getMyMemberDTO) {
        //参数校验
        Result result = memberValidateService.validateGetMyMemberDTO(getMyMemberDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TMember> tMemberIPage = memberService.getMemberByCondition(getMyMemberDTO);
        MemberListVO memberListVO = GenerateUtil.getMemberListVO(tMemberIPage);
        //设置memberTypeName
        List<TMemberType> allType = memberTypeService.getAllType();
        GenerateUtil.setMemberTypeName(memberListVO.getMemberListRowVOList(),allType);
        //返回
        return Result.success(memberListVO);
    }

    @Override
    public Result getMemberDetail(Integer memberId) {
        //参数校验
        
        //数据查询
        
        //返回
    }

    @Override
    public Result createMember(CreateMemberDTO createMemberDTO) {
        //参数校验
        Result result = memberValidateService.validateCreateMemberDTO(createMemberDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成实体类
        TMember tMember = GenerateUtil.getTMember(createMemberDTO);
        //入库
        memberService.save(tMember);
        //返回
        return Result.success();
    }

    @Override
    public Result checkMemberName(String memberName) {
        TMember tMember = memberService.getMemberByMemberName(memberName);
        if(ObjectUtil.isNotEmpty(tMember)){
            return Result.getResult(ResultEnum.EXIST_MEMBER_NAME);
        }
        return Result.success();
    }

    @Override
    public Result editMember(EditMemberDTO editMemberDTO) {
        //参数校验
        Result result = memberValidateService.validateEditMemberDTO(editMemberDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //修改数据
        TMember tMember = GenerateUtil.getTMember(editMemberDTO);
        //入库
        memberService.saveOrUpdate(tMember);
        //返回
        return Result.success();
    }
}
