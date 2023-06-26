package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateCaseDTO;
import com.company.scma.common.dto.EditCaseDTO;
import com.company.scma.common.dto.GetCaseDTO;
import com.company.scma.common.vo.Result;

public interface CaseBizService {
    
    public Result getAllCase(GetCaseDTO getCaseDTO);
    
    public Result getCaseDetail(Integer caseId);
    
    public Result createCase(CreateCaseDTO createCaseDTO);
    
    public Result editCase(EditCaseDTO editCaseDTO);
    
    public Result deleteCase(Integer caseId);
}
