package com.company.scma.service.impl;

import com.company.scma.common.po.TMemberType;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.MemberTypeVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.MemberTypeBizService;
import com.company.scma.service.mapperservice.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberTypeBizServiceImpl implements MemberTypeBizService {
    @Autowired
    private MemberTypeService memberTypeService;
    
    @Override
    public Result getAllMemberType() {
        List<TMemberType> allType = memberTypeService.getAllType();
        List<MemberTypeVO> memberTypeVOList = GenerateUtil.getMemberTypeVOList(allType);
        return Result.success(memberTypeVOList);
    }

    @Override
    public Result createMemberType(List<String> memberTypeName) {
        return null;
    }

    @Override
    public Result deleteMemberType(Integer memberTypeId) {
        return null;
    }
}
