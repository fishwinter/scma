package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateCaseDTO;
import com.company.scma.common.dto.EditCaseDTO;
import com.company.scma.common.dto.GetCaseDTO;
import com.company.scma.common.vo.Result;

public interface CaseValidateService {
    public Result validateGetCaseDTO(GetCaseDTO getCaseDTO);
    public Result validateCreateCaseDTO(CreateCaseDTO createCaseDTO);
    public Result validateEditCaseDTO(EditCaseDTO editCaseDTO);
}
