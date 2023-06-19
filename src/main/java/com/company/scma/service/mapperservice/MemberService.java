package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetMemberDataBaseDTO;
import com.company.scma.common.dto.GetMyMemberDTO;
import com.company.scma.common.po.TMember;

import java.util.List;

public interface MemberService extends IService<TMember> {
    public TMember getMemberByMemberName(String memberName);
    
    public IPage<TMember> getMemberByCondition(GetMyMemberDTO getMyMemberDTO);
    
    public IPage<TMember> getMemberByCondition(GetMemberDataBaseDTO getMemberDataBaseDTO);
    
    public TMember getMemberByMemberId(Integer memberId);

    public List<TMember> getMemberByMemberType(Integer memberTypeId);
    
    public void deleteMemberByMemberId(Integer memberId);
    
    public List<TMember> getMemberByOwnerUserid(Integer ownerUserid);
    
    public List<TMember> getMemberByOwnerUserid(List<Integer> ownerUseridList,Integer deleteflag);
    
    public List<TMember> getMemberByOwnerPartnershipId(Integer ownerPartnershipId);
}
