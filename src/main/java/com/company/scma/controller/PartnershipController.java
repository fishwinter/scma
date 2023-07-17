package com.company.scma.controller;

import cn.hutool.core.codec.Base64;
import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.PartnershipBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "partnership", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnershipController {
    
    @Autowired
    private PartnershipBizService partnershipBizService;

    @RequestMapping(value = "/getPartnership", method = RequestMethod.POST)
    @RequiresPermissions("partnership:visit")
    public Result getPartnership(@RequestBody GetPartnershipDTO getPartnershipDTO){
        return partnershipBizService.getPartnership(getPartnershipDTO);
    }

    @RequestMapping(value = "/getPartnershipDetail", method = RequestMethod.POST)
    @RequiresPermissions("partnership:visit")
    public Result getPartnershipDetail(@RequestParam Integer partnershipId){
        return partnershipBizService.getPartnershipDetail(partnershipId);
    }

    @RequestMapping(value = "/downloadPartnershipData", method = RequestMethod.POST)
    @RequiresPermissions("partnership:visit")
    public Result downloadPartnershipData(@RequestBody GetPartnershipDTO getPartnershipDTO){
        return partnershipBizService.downloadPartnershipData(getPartnershipDTO);
    }

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

    @RequestMapping(value = "/deletePartnership", method = RequestMethod.POST)
    @RequiresPermissions("partnership:edit")
    public Result deletePartnership(@RequestParam Integer partnershipId){
        return partnershipBizService.deletePartnership(partnershipId);
    }
}
