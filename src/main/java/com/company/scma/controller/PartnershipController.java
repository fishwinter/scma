package com.company.scma.controller;

import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.PartnershipBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "partnership", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnershipController {
    
    @Autowired
    private PartnershipBizService partnershipBizService;

    @RequestMapping(value = "/createPartnership", method = RequestMethod.POST)
    @RequiresPermissions("partnership:add")
    public Result createPartnership(@RequestBody CreatePartnershipDTO createPartnershipDTO){
        return partnershipBizService.createPartnership(createPartnershipDTO);
    }

    @RequestMapping(value = "/editPartnership", method = RequestMethod.POST)
    @RequiresPermissions("partnership:edit")
    public Result editPartnership(@RequestBody EditPartnershipDTO editPartnershipDTO){
        return partnershipBizService.editPartnership(editPartnershipDTO);
    }
    
    
}
