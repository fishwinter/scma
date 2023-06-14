package com.company.scma.service.impl;

import com.company.scma.common.po.TMemberType;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.MemberTypeVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.MemberTypeBizService;
import com.company.scma.service.mapperservice.MemberTypeService;
import com.company.scma.service.validateservice.MemberTypeValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberTypeBizServiceImpl implements MemberTypeBizService {
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private MemberTypeValidateService memberTypeValidateService;
    
    @Override
    public Result getAllMemberType() {
        List<TMemberType> allType = memberTypeService.getAllType();
        List<MemberTypeVO> memberTypeVOList = GenerateUtil.getMemberTypeVOList(allType);
        return Result.success(memberTypeVOList);
    }

    @Override
    @Transactional
    public Result createMemberType(List<String> memberTypeNameList) {
        //创建实体
        List<TMemberType> tMemberTypeList = GenerateUtil.getTMemberTypeList(memberTypeNameList);
        //插入
        memberTypeService.saveBatch(tMemberTypeList);
        //返回
        return Result.success();

    }

    @Override
    @Transactional
    public Result deleteMemberType(Integer memberTypeId) {
        //参数校验
        Result result = memberTypeValidateService.validateDeleteMemberTypeId(memberTypeId);
        //删除memberType
        if(!Result.isSuccess(result)){
            return result;
        }
        memberTypeService.deleteMemberTypeById(memberTypeId);
        //返回
        return Result.success();
    }
}
