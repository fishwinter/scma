package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreatePartnershipDTO;
import com.company.scma.common.po.TOperation;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.OperationService;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.PartnershipValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PartnershipValidateServiceImpl implements PartnershipValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    @Autowired
    private OperationService operationService;
    @Override
    public Result validateCreatePartnershipDTO(CreatePartnershipDTO createPartnershipDTO) {
        if(ObjectUtil.isEmpty(createPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(createPartnershipDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        //校验绑定的活动状态
        Integer operationId = createPartnershipDTO.getOperationId();
        TOperation tOperationById = operationService.getTOperationById(operationId);
        if(Constant.OperationStatus.NOT_STARTED.equals(tOperationById.getStatus())){
            return Result.getResult(ResultEnum.ERROR_OPERATION_STATUS);
        }
        return Result.success(tOperationById.getStatus());
    }
}
