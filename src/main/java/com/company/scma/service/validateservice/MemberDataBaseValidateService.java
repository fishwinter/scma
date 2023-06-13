package com.company.scma.service.validateservice;

import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.vo.Result;

public interface MemberDataBaseValidateService {
    public Result validateGetMemberDataBaseDTO(GetMemberDataBaseDTO getMemberDataBaseDTO);
    
    public Result validateAdoptMemberId(Integer memberId);
}
