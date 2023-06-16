package com.company.scma.controller;

import com.company.scma.common.dto.CreateSupplierDTO;
import com.company.scma.common.dto.EditSupplierDTO;
import com.company.scma.common.dto.GetSupplierDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.SupplierBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "supplier", produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierController {
    @Autowired
    private SupplierBizService supplierBizService;

    @RequestMapping(value = "/getSupplier", method = RequestMethod.POST)
    @RequiresPermissions("supplier:visit")
    public Result getSupplier(@RequestBody GetSupplierDTO getSupplierDTO){
        return supplierBizService.getSupplier(getSupplierDTO);
    }
    
    @RequestMapping(value = "/getSupplierDetail", method = RequestMethod.POST)
    @RequiresPermissions("supplier:visit")
    public Result getSupplierDetail(@RequestParam Integer supplierId){
        return supplierBizService.getSupplierDetail(supplierId);
    }
    
    @RequestMapping(value = "/createSupplier", method = RequestMethod.POST)
    @RequiresPermissions("supplier:add")
    public Result createSupplier(@RequestBody CreateSupplierDTO createSupplierDTO){
        return supplierBizService.createSupplier(createSupplierDTO);
    }

    @RequestMapping(value = "/editSupplier", method = RequestMethod.POST)
    @RequiresPermissions("supplier:edit")
    public Result editSupplier(@RequestBody EditSupplierDTO editSupplierDTO){
        return supplierBizService.editSupplier(editSupplierDTO);
    }

    @RequestMapping(value = "/deleteSupplier", method = RequestMethod.POST)
    @RequiresPermissions("supplier:delete")
    public Result deleteSupplier(@RequestParam Integer supplierId){
        return supplierBizService.deleteSupplier(supplierId);
    }
    
}
