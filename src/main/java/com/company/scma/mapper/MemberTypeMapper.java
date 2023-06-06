package com.company.scma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.scma.common.po.TMemberType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberTypeMapper extends BaseMapper<TMemberType> {
}