package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.vo.Result;

public interface PartnershipBizService {

    public Result getPartnership(GetPartnershipDTO getPartnershipDTO);
    
    public Result downloadPartnershipData(GetPartnershipDTO getPartnershipDTO);

    public Result getPartnershipDetail(Integer partnershipId);

    public Result createPartnership (CreatePartnershipDTO createPartnershipDTO);
    
    public Result editPartnership (EditPartnershipDTO editPartnershipDTO);

    public Result deletePartnership(Integer partnershipId);
}
