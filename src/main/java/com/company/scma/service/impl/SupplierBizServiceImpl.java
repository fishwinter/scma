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

import java.util.Arrays;
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
//        //查询所有memberType
//        List<TMemberType> allType = memberTypeService.getAllType();
//        List<MemberTypeVO> allMemberTypeVO = GenerateUtil.getMemberTypeVOList(allType);
//        //查询当前memberType
//        TMemberType tMemberType = memberTypeService.getMemberTypeByMemberTypeId(tSupplier.getMemberTypeId());
//        MemberTypeVO myMemberTypeVO = GenerateUtil.getMemberTypeVO(tMemberType);
        //查询所有单位类型
        String enterpriseTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.ENTERPRISE_TYPE);
        List<EnterpriseTypeVO> allEnterpriseType = JSON.parseArray(enterpriseTypeStr, EnterpriseTypeVO.class);
        //查询当前单位类型
        EnterpriseTypeVO myEnterpriseType = GenerateUtil.getEnterpriseTypeVO(allEnterpriseType, tSupplier.getEnterpriseTypeId());
        //查询所有企业性质
        String partnershipTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_TYPE);
        List<PartnershipTypeVO> allPartnershipType = JSON.parseArray(partnershipTypeStr, PartnershipTypeVO.class);
        //查询当前企业性质
        PartnershipTypeVO myPartnershipType = GenerateUtil.getPartnershipTypeVO(allPartnershipType, tSupplier.getPartnershipType());
        //查询所有活动类型
        String partnershipProjectTypeStr
                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_PROJECT_TYPE);
        List<PartnershipProjectTypeVO> allPartnershipProjectType = JSON.parseArray(partnershipProjectTypeStr, PartnershipProjectTypeVO.class);
        //获取当前企业活动类型
        String projectType = tSupplier.getProjectType();
        List<PartnershipProjectTypeVO> myPartnershipProjectType = null;
        if(ObjectUtil.isNotEmpty(projectType)){
            String[] split = projectType.split(",");
            List<String> projectTypeList = Arrays.asList(split);
            myPartnershipProjectType = GenerateUtil.getPartnershipProjectTypeVO(allPartnershipProjectType, projectTypeList);
        }
        //查询所有负责人职务类型
        String directorPositionTypeStr
                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.DIRECTOR_POSITION_TYPE);
        List<PositionVO> allDirectorPositionList = JSON.parseArray(directorPositionTypeStr, PositionVO.class);
        //查询所有联系人职务类型
        String contactPositionTypeStr
                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.CONTACT_POSITION_TYPE);
        List<PositionVO> allContactPositionList = JSON.parseArray(contactPositionTypeStr, PositionVO.class);
        //获取联系人职务类型
        PositionVO contactPosition = GenerateUtil.getPositionVO(allContactPositionList, tSupplier.getContactPositionId());
        //获取负责人职务类型
        PositionVO directorPosition = GenerateUtil.getPositionVO(allDirectorPositionList, tSupplier.getDirectorPositionId());
        //组装数据
        supplierDetailVO.setAllEnterpriseType(allEnterpriseType);
        supplierDetailVO.setMyEnterpriseTypeVO(myEnterpriseType);
        supplierDetailVO.setMyPartnershipType(myPartnershipType);
        supplierDetailVO.setAllPartnershipType(allPartnershipType);
        supplierDetailVO.setMyProjectType(myPartnershipProjectType);
        supplierDetailVO.setAllProjectType(allPartnershipProjectType);
        supplierDetailVO.setAllContactPosition(allContactPositionList);
        supplierDetailVO.setContactsPosition(contactPosition);
        supplierDetailVO.setDirectorPosition(directorPosition);
        supplierDetailVO.setAllDirectorPosition(allDirectorPositionList);
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
