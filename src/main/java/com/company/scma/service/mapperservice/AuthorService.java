package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetAuthorDTO;
import com.company.scma.common.po.TAuthor;

public interface AuthorService extends IService<TAuthor> {
    public IPage<TAuthor> getAuthorByCondition(GetAuthorDTO getAuthorDTO);

    public TAuthor getAuthorById(Integer authorId);
    
    public void deleteAuthorById(Integer authorId);
}
