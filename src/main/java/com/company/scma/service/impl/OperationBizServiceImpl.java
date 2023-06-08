package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateOperationDTO;
import com.company.scma.common.dto.EditOperationDTO;
import com.company.scma.common.dto.GetOperationDTO;
import com.company.scma.common.po.TOperation;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.OperationDetailVO;
import com.company.scma.common.vo.OperationListRowVO;
import com.company.scma.common.vo.OperationListVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.OperationBizService;
import com.company.scma.service.mapperservice.OperationOtmPartnershipService;
import com.company.scma.service.mapperservice.OperationService;
import com.company.scma.service.validateservice.OperationValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationBizServiceImpl implements OperationBizService {
    @Autowired
    private OperationValidateService operationValidateService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private OperationOtmPartnershipService operationOtmPartnershipService;

    @Override
    public Result getOperation(GetOperationDTO getOperationDTO) {
        //参数校验
        Result result = operationValidateService.validateGetOperationDTO(getOperationDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TOperation> tOperationIPage = operationService.getTOperationByCondition(getOperationDTO);
        //数据组装
        OperationListVO operationListVO = GenerateUtil.getOperationListVO(tOperationIPage);
        //返回
        return Result.success(operationListVO);
    }

    @Override
    public Result getOperationDetail(Integer operationId) {
        //数据校验
        if(ObjectUtil.isEmpty(operationId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //数据查询
        TOperation tOperationById = operationService.getTOperationById(operationId);
        //数据组装
        if(ObjectUtil.isEmpty(tOperationById)){
            return Result.success();
        }
        OperationDetailVO operationDetailVO = GenerateUtil.getOperationDetailVO(tOperationById);
        //返回数据
        return Result.success(operationDetailVO);
    }

    @Override
    public Result getAllValidOperation() {
        //查询数据
        List<TOperation> allValidOperation = operationService.getAllValidOperation();
        //封装返回数据
        if(ObjectUtil.isEmpty(allValidOperation)){
            return Result.success();
        }
        List<OperationListRowVO> operationListRowVOList = allValidOperation.stream().map(GenerateUtil::getOperationListRowVO).collect(Collectors.toList());
        //返回
        return Result.success(operationListRowVOList);
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
    @Transactional
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
