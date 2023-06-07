package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.dto.EditMemberDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.vo.Result;

public interface MemberBizService {
    public Result getMyMember(GetMyMemberDTO getMyMemberDTO);
    
    public Result getMemberDetail(Integer memberId);
    
    public Result createMember(CreateMemberDTO createMemberDTO);
    
    public Result checkMemberName(String memberName);
    
    public Result editMember(EditMemberDTO editMemberDTO);
    
    public Result deleteMember(Integer memberId);
}
