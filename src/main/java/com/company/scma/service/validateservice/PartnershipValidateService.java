package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.dto.EditPartnershipDTO;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.vo.Result;

public interface PartnershipValidateService {
    public Result validateCreatePartnershipDTO(CreatePartnershipDTO createPartnershipDTO);
    
    public Result validateGetPartnershipDTO(GetPartnershipDTO getPartnershipDTO);
    
    public Result validateEditPartnershipDTO(EditPartnershipDTO editPartnershipDTO);
}
