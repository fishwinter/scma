package com.company.scma.controller;

import com.company.scma.common.dto.SysConfigDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.SysConfigBizService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "sysConfig", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysConfigController {
    @Autowired
    private SysConfigBizService sysConfigBizService;

    @RequestMapping(value = "/getSysConfig", method = RequestMethod.POST)
    @RequiresPermissions("sysConfig:visit")
    public Result getSysConfig(){
        return sysConfigBizService.getSysConfig();
    }


    @RequestMapping(value = "/editSysConfig", method = RequestMethod.POST)
    @RequiresPermissions("sysConfig:edit")
    public Result editSysConfig(@RequestBody SysConfigDTO sysConfigDTO){
        return sysConfigBizService.editSysConfig(sysConfigDTO);
    }

    @RequestMapping(value = "/getPartnershipConfig", method = RequestMethod.POST)
    @RequiresPermissions(value = {"partnership:add","supplier:add"},logical = Logical.OR)
    public Result getPartnershipConfig(){
        return sysConfigBizService.getPartnershipConfig();
    }

    @RequestMapping(value = "/getCaseType", method = RequestMethod.POST)
    @RequiresPermissions("case:add")
    public Result getCaseType(){
        return sysConfigBizService.getCaseType();
    }

}
