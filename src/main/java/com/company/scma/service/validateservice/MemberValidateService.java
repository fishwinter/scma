package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateMemberDTO;
import com.company.scma.common.dto.EditMemberDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.vo.Result;

public interface MemberValidateService {
    public Result validateCreateMemberDTO(CreateMemberDTO createMemberDTO);
    
    public Result validateEditMemberDTO(EditMemberDTO editMemberDTO);
    
    public Result validateGetMyMemberDTO(GetMyMemberDTO getMyMemberDTO);
}
