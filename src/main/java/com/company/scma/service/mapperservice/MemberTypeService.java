package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TMemberType;

import java.util.List;

public interface MemberTypeService extends IService<TMemberType> {
    public TMemberType getMemberTypeByMemberTypeId(Integer memberTypeId);
    
    public List<TMemberType> getAllType();

    public void deleteMemberTypeById(Integer memberTypeId);
}
