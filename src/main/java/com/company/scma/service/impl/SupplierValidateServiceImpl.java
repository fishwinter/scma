package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateSupplierDTO;
import com.company.scma.common.dto.EditSupplierDTO;
import com.company.scma.common.dto.GetSupplierDTO;
import com.company.scma.common.po.TMemberType;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.MemberTypeService;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.SupplierValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierValidateServiceImpl implements SupplierValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    @Autowired
    private MemberTypeService memberTypeService;

    @Override
    public Result validateCreateSupplierDTO(CreateSupplierDTO createSupplierDTO) {
        if (ObjectUtil.isEmpty(createSupplierDTO)) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if (!commonValidateService.validateAnnotation(createSupplierDTO)) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        return Result.success();
    }

    @Override
    public Result validateEditSupplierDTO(EditSupplierDTO editSupplierDTO) {
        if (ObjectUtil.isEmpty(editSupplierDTO)) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if (!commonValidateService.validateAnnotation(editSupplierDTO)) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        //校验会员类型是否正确
        TMemberType tMemberType = memberTypeService.getMemberTypeByMemberTypeId(editSupplierDTO.getMemberTypeId());
        if (ObjectUtil.isEmpty(tMemberType)) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        return Result.success();
    }

    @Override
    public Result validateGetSupplierDTO(GetSupplierDTO getSupplierDTO) {
        if (ObjectUtil.isEmpty(getSupplierDTO)) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if (!commonValidateService.validateAnnotation(getSupplierDTO)) {
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        return Result.success();
    }
}
