package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetPartnershipDTO;
import com.company.scma.common.po.TPartnership;

import java.util.List;

public interface PartnershipService extends IService<TPartnership> {
    public IPage<TPartnership> getPartnershipByCondition(GetPartnershipDTO getPartnershipDTO);
    
    public TPartnership getTPartnershipById(Integer partnershipId);
    
    public void deleteByPartnershipId(Integer partnershipId);
    
    public List<TPartnership> getTPartnershipByName(String partnershipName);
    
    public List<TPartnership> getTPartnershipByOperationId(Integer operationId,Integer deleteflag);
    
    public List<TPartnership> fuzzQueryTPartnershipByPartnershipName(String partnershipName);
}
