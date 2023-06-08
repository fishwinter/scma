package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.vo.Result;

public interface PartnershipValidateService {
    public Result validateCreatePartnershipDTO(CreatePartnershipDTO createPartnershipDTO);
}
