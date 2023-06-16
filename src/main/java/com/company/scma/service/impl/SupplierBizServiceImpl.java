package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateSupplierDTO;
import com.company.scma.common.dto.EditSupplierDTO;
import com.company.scma.common.dto.GetSupplierDTO;
import com.company.scma.common.po.TMemberType;
import com.company.scma.common.po.TSupplier;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.*;
import com.company.scma.service.bizservice.SupplierBizService;
import com.company.scma.service.mapperservice.MemberTypeService;
import com.company.scma.service.mapperservice.SupplierService;
import com.company.scma.service.mapperservice.SysConfigService;
import com.company.scma.service.validateservice.SupplierValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierBizServiceImpl implements SupplierBizService {
    @Autowired
    private SupplierValidateService supplierValidateService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private SysConfigService sysConfigService;
    @Override
    @Transactional
    public Result createSupplier(CreateSupplierDTO createSupplierDTO) {
        //参数校验
        Result result = supplierValidateService.validateCreateSupplierDTO(createSupplierDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成实体
        TSupplier tSupplier = GenerateUtil.getTSupplier(createSupplierDTO);
        //插入
        supplierService.save(tSupplier);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result editSupplier(EditSupplierDTO editSupplierDTO) {
        //参数校验
        Result result = supplierValidateService.validateEditSupplierDTO(editSupplierDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成实体
        TSupplier tSupplier = GenerateUtil.getTSupplier(editSupplierDTO);
        //插入
        supplierService.saveOrUpdate(tSupplier);
        //返回
        return Result.success();
    }

    @Override
    public Result getSupplier(GetSupplierDTO getSupplierDTO) {
        //参数校验
        Result result = supplierValidateService.validateGetSupplierDTO(getSupplierDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //数据查询
        IPage<TSupplier> tSupplierIPage = supplierService.getTSupplierByCondition(getSupplierDTO);
        //数据封装
        SupplierListVO supplierListVO = GenerateUtil.getSupplierListVO(tSupplierIPage);
        //返回
        return Result.success(supplierListVO);
    }

    @Override
    public Result getSupplierDetail(Integer supplierId) {
        //参数校验
        if(ObjectUtil.isEmpty(supplierId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //查询实体
        TSupplier tSupplier = supplierService.getTSupplierById(supplierId);
        if(ObjectUtil.isEmpty(tSupplier)){
            return Result.success();
        }
        //生成实体
        SupplierDetailVO supplierDetailVO = GenerateUtil.getSupplierDetailVO(tSupplier);
        //查询所有memberType
        List<TMemberType> allType = memberTypeService.getAllType();
        List<MemberTypeVO> allMemberTypeVO = GenerateUtil.getMemberTypeVOList(allType);
        //查询当前memberType
        TMemberType tMemberType = memberTypeService.getMemberTypeByMemberTypeId(tSupplier.getMemberTypeId());
        MemberTypeVO myMemberTypeVO = GenerateUtil.getMemberTypeVO(tMemberType);
        //查询所有企业性质
        String partnershipTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_TYPE);
        List<PartnershipTypeVO> allPartnershipType = JSON.parseArray(partnershipTypeStr, PartnershipTypeVO.class);
        //查询当前企业性质
        PartnershipTypeVO myPartnershipType = GenerateUtil.getPartnershipTypeVO(allPartnershipType, tSupplier.getPartnershipType());
        //组装数据
        supplierDetailVO.setMyMemberType(myMemberTypeVO);
        supplierDetailVO.setAllMemberType(allMemberTypeVO);
        supplierDetailVO.setMyPartnershipType(myPartnershipType);
        supplierDetailVO.setAllPartnershipType(allPartnershipType);
        //返回
        return Result.success(supplierDetailVO);
    }

    @Override
    public Result deleteSupplier(Integer supplierId) {
        //参数校验
        if(ObjectUtil.isEmpty(supplierId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //删除
        supplierService.deleteSupplierById(supplierId);
        //返回
        return Result.success();
    }
}
