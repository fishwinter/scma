package com.company.scma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.scma.common.po.TAuthor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper extends BaseMapper<TAuthor> {
}