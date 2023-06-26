package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateAuthorDTO;
import com.company.scma.common.dto.EditAuthorDTO;
import com.company.scma.common.dto.GetAuthorDTO;
import com.company.scma.common.vo.Result;

public interface AuthorBizService {
    public Result getAuthorDetail(Integer authorId);
    
    public Result getAllAuthor(GetAuthorDTO getAuthorDTO);
    
    public Result createAuthor(CreateAuthorDTO createAuthorDTO);
    
    public Result editAuthor(EditAuthorDTO editAuthorDTO);
    
    public Result deleteAuthor(Integer authorId);
}
