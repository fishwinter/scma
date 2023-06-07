package com.company.scma.controller;

import com.company.scma.common.dto.CreateOperationDTO;
import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.common.dto.EditOperationDTO;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.OperationBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "operation", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationController {
    @Autowired
    private OperationBizService operationBizService;

    @RequestMapping(value = "/getAllOperation", method = RequestMethod.POST)
    @RequiresPermissions("operation:visit")
    public Result getAllOperation(@RequestBody GetOperationDTO getOperationDTO){
        return operationBizService.getAllOperation(getOperationDTO);
    }

    @RequestMapping(value = "/createOperation", method = RequestMethod.POST)
    @RequiresPermissions("operation:add")
    public Result createOperation(@RequestBody CreateOperationDTO createRoleDTO){
        return operationBizService.createOperation(createRoleDTO);
    }

    @RequestMapping(value = "/editOperation", method = RequestMethod.POST)
    @RequiresPermissions("operation:edit")
    public Result editOperation(@RequestBody EditOperationDTO editOperationDTO){
        return operationBizService.editOperation(editOperationDTO);
    }

    @RequestMapping(value = "/deleteOperation", method = RequestMethod.POST)
    @RequiresPermissions("operation:delete")
    public Result deleteOperation(@RequestParam Integer operationId){
        return operationBizService.deleteOperation(operationId);
    }
}
