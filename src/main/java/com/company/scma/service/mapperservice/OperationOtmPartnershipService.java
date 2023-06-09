package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TOperationOtmPartnership;

public interface OperationOtmPartnershipService extends IService<TOperationOtmPartnership> {
    public void deleteByOperationId (Integer operationId);
    
    public void deleteByPartnershipIdAndOperationId(Integer partnershipId, Integer operationId);
    
    public void deleteByPartnershipId(Integer partnershipId);
}
