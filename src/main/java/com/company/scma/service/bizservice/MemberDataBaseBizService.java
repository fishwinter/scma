package com.company.scma.service.bizservice;

import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.vo.Result;

public interface MemberDataBaseBizService {

    public Result getMyMemberDataBase(GetMemberDataBaseDTO getMemberDataBaseDTO);

    public Result adoptMember(Integer memberId);
}
