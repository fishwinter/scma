package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateAuthorDTO;
import com.company.scma.common.dto.EditAuthorDTO;
import com.company.scma.common.dto.GetAuthorDTO;
import com.company.scma.common.vo.Result;

public interface AuthorValidateService {
    public Result validateGetAuthorDTO(GetAuthorDTO getAuthorDTO);
    public Result validateCreateAuthorDTO(CreateAuthorDTO createAuthorDTO);
    public Result validateEditAuthorDTO(EditAuthorDTO editAuthorDTO);
}
