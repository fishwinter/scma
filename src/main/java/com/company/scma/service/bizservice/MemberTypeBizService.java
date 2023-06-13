package com.company.scma.service.bizservice;

import com.company.scma.common.vo.Result;

import java.util.List;

public interface MemberTypeBizService {
    public Result getAllMemberType();

    public Result createMemberType(List<String> memberTypeNameList);

    public Result deleteMemberType(Integer memberTypeId);
}
