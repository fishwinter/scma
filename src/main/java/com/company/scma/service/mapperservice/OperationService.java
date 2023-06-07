package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.po.TOperation;

public interface OperationService extends IService<TOperation> {
    public void deleteOperationById(Integer operationId);

    public IPage<TOperation> getTOperationByCondition(GetOperationDTO getOperationDTO);
}
