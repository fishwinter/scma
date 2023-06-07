package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateOperationDTO;
import com.company.scma.common.dto.EditOperationDTO;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.po.TOperation;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.OperationBizService;
import com.company.scma.service.mapperservice.OperationOtmPartnershipService;
import com.company.scma.service.mapperservice.OperationService;
import com.company.scma.service.validateservice.OperationValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationBizServiceImpl implements OperationBizService {
    @Autowired
    private OperationValidateService operationValidateService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private OperationOtmPartnershipService operationOtmPartnershipService;

    @Override
    public Result getAllOperation(GetOperationDTO getOperationDTO) {
        //参数校验
        Result result = operationValidateService.validateGetOperationDTO(getOperationDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TOperation> tOperationIPage = operationService.getTOperationByCondition(getOperationDTO);
        //数据组装
        
        //返回
        return null;
    }

    @Override
    @Transactional
    public Result createOperation(CreateOperationDTO createOperationDTO) {
        //参数校验
        Result result = operationValidateService.validateCreateOperationDTO(createOperationDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成实体类
        TOperation tOperation = GenerateUtil.getTOperation(createOperationDTO);
        //入库
        operationService.save(tOperation);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result editOperation(EditOperationDTO editOperationDTO) {
        //参数校验
        Result result = operationValidateService.validateEditOperationDTO(editOperationDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成数据
        TOperation tOperation = GenerateUtil.getTOperation(editOperationDTO);
        //入库
        operationService.saveOrUpdate(tOperation);
        //返回
        return Result.success();
    }

    @Override
    public Result deleteOperation(Integer operationId) {
        //参数校验
        if(ObjectUtil.isEmpty(operationId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //删除活动
        operationService.deleteOperationById(operationId);
        //返回
        return Result.success();
    }
}
