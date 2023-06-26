package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetCaseDTO;
import com.company.scma.common.po.TCase;

public interface CaseService extends IService<TCase> {
    public void deleteCaseById(Integer caseId);
    
    public IPage<TCase> getCaseByCondition(GetCaseDTO getCaseDTO);
    
    public TCase getTCaseById(Integer caseId);
}
