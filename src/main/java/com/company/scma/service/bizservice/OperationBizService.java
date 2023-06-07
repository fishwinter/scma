package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateOperationDTO;
import com.company.scma.common.dto.EditOperationDTO;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.vo.Result;

public interface OperationBizService {
    public Result getAllOperation(GetOperationDTO getOperationDTO);
    
    public Result createOperation(CreateOperationDTO createOperationDTO);
    
    public Result editOperation(EditOperationDTO editOperationDTO);
    
    public Result deleteOperation(Integer operationId);
}
