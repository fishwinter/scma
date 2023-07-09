package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetSupplierDTO;
import com.company.scma.common.po.TSupplier;

public interface SupplierService extends IService<TSupplier> {
    public TSupplier getTSupplierById(Integer supplierId);

    public IPage<TSupplier> getTSupplierByCondition(GetSupplierDTO getSupplierDTO);
    
    public void deleteSupplierById(Integer supplierId);

    public TSupplier getTSupplierByName(String supplierName);
}
