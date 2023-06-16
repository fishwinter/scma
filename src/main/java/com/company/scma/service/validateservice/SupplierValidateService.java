package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateSupplierDTO;
import com.company.scma.common.dto.EditSupplierDTO;
import com.company.scma.common.dto.GetSupplierDTO;
import com.company.scma.common.vo.Result;

public interface SupplierValidateService {
    public Result validateCreateSupplierDTO(CreateSupplierDTO createSupplierDTO);
    public Result validateEditSupplierDTO(EditSupplierDTO editSupplierDTO);
    public Result validateGetSupplierDTO(GetSupplierDTO getSupplierDTO);
}
