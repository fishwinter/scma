package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.po.TMember;

public interface MemberService extends IService<TMember> {
    public TMember getMemberByMemberName(String memberName);
    
    public IPage<TMember> getMemberByCondition(GetMyMemberDTO getMyMemberDTO);
    
    public IPage<TMember> getMemberByCondition(GetMemberDataBaseDTO getMemberDataBaseDTO);
    
    public TMember getMemberByMemberId(Integer memberId);
    
    public void deleteMemberByMemberId(Integer memberId);
}
