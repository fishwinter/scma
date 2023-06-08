package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.vo.Result;

public interface PartnershipBizService {
    
    public Result createPartnership (CreatePartnershipDTO createPartnershipDTO);
    
    public Result editPartnership (EditPartnershipDTO editPartnershipDTO);
}
