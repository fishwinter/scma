package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateSupplierDTO;
import com.company.scma.common.dto.EditSupplierDTO;
import com.company.scma.common.dto.GetSupplierDTO;
import com.company.scma.common.vo.Result;

public interface SupplierBizService {
    public Result createSupplier(CreateSupplierDTO createSupplierDTO);
    
    public Result editSupplier(EditSupplierDTO editSupplierDTO);
    
    public Result getSupplier(GetSupplierDTO getSupplierDTO);
    
    public Result getSupplierDetail(Integer supplierId);
    
    public Result deleteSupplier(Integer supplierId);
}
