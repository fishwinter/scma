package com.company.scma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.scma.common.po.TCase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaseMapper extends BaseMapper<TCase> {
}