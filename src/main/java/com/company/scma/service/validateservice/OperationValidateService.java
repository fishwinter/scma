package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateOperationDTO;
import com.company.scma.common.dto.EditOperationDTO;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.vo.Result;

public interface OperationValidateService {
    public Result validateCreateOperationDTO(CreateOperationDTO createOperationDTO);
    
    public Result validateEditOperationDTO(EditOperationDTO editOperationDTO);
    
    public Result validateGetOperationDTO(GetOperationDTO getOperationDTO);
}
