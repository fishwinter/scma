package com.company.scma.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TOperationOtmPartnership;
import com.company.scma.mapper.OperationOtmPartnershipMapper;
import com.company.scma.service.mapperservice.OperationOtmPartnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationOtmPartnershipServiceImpl extends ServiceImpl<OperationOtmPartnershipMapper, TOperationOtmPartnership> implements OperationOtmPartnershipService {
    @Autowired
    private OperationOtmPartnershipMapper operationOtmPartnershipMapper;
    @Override
    public void deleteByOperationId(Integer operationId) {
        UpdateWrapper<TOperationOtmPartnership> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.OPERATION_ID,operationId);
        operationOtmPartnershipMapper.update(null,updateWrapper);
    }
}
