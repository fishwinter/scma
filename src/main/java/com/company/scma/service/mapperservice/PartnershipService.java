package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.po.TPartnership;

public interface PartnershipService extends IService<TPartnership> {
    public IPage<TPartnership> getPartnershipByCondition(GetPartnershipDTO getPartnershipDTO);
    
    public TPartnership getTPartnershipById(Integer partnershipId);
}
