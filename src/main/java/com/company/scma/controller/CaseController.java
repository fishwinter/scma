package com.company.scma.controller;

import com.company.scma.common.dto.CreateCaseDTO;
import com.company.scma.common.dto.EditCaseDTO;
import com.company.scma.common.dto.GetCaseDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.CaseBizService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "case", produces = MediaType.APPLICATION_JSON_VALUE)
public class CaseController {
    @Autowired
    private CaseBizService caseBizService;

    @RequiresPermissions("case:visit")
    @RequestMapping(value = "/getAllCase", method = RequestMethod.POST)
    public Result getAllCase(@RequestBody GetCaseDTO getCaseDTO){
        return caseBizService.getAllCase(getCaseDTO);
    }

    @RequiresPermissions("case:visit")
    @RequestMapping(value = "/getCaseDetail", method = RequestMethod.POST)
    public Result getCaseDetail(@RequestParam Integer caseId){
        return caseBizService.getCaseDetail(caseId);
    }

    @RequestMapping(value = "/createCase", method = RequestMethod.POST)
    @RequiresPermissions("case:add")
    public Result createCase(@RequestBody CreateCaseDTO createCaseDTO){
        return caseBizService.createCase(createCaseDTO);
    }

    @RequestMapping(value = "/editCase", method = RequestMethod.POST)
    @RequiresPermissions("case:edit")
    public Result editCase(@RequestBody EditCaseDTO editCaseDTO){
        return caseBizService.editCase(editCaseDTO);
    }

    @RequestMapping(value = "/deleteCase", method = RequestMethod.POST)
    @RequiresPermissions("case:delete")
    public Result deleteCase(@RequestParam Integer caseId){
        return caseBizService.deleteCase(caseId);
    }
}
