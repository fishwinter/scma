package com.company.scma.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.po.TPartnership;
import com.company.scma.mapper.PartnershipMapper;
import com.company.scma.service.mapperservice.PartnershipService;
import org.springframework.stereotype.Service;

@Service
public class PartnershipServiceImpl extends ServiceImpl<PartnershipMapper, TPartnership> implements PartnershipService {
}
