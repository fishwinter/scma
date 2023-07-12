package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.SysConfigDTO;
import com.company.scma.common.po.TMemberType;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.*;
import com.company.scma.service.bizservice.SysConfigBizService;
import com.company.scma.service.mapperservice.MemberTypeService;
import com.company.scma.service.mapperservice.SysConfigService;
import com.company.scma.service.validateservice.SysConfigValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysConfigBizServiceImpl implements SysConfigBizService {
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private SysConfigValidateService sysConfigValidateService;
    @Override
    public Result getSysConfig() {
        //查询配置
        SysConfigDetailVO sysConfigDetailVO = new SysConfigDetailVO();
        String suspendedTerm = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.SUSPENDED_TERM);
        String releaseTerm = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_TERM);
        String releaseWay = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_WAY);
        //查询会员类型
        List<TMemberType> allType = memberTypeService.getAllType();
        List<MemberTypeVO> memberTypeVOList = GenerateUtil.getMemberTypeVOList(allType);
        //封装
        sysConfigDetailVO.setSuspendedTerm(Integer.valueOf(suspendedTerm));
        sysConfigDetailVO.setReleaseTerm(Integer.valueOf(releaseTerm));
        sysConfigDetailVO.setReleaseWay(Integer.valueOf(releaseWay));
        sysConfigDetailVO.setMemberTypeVOList(memberTypeVOList);
        //返回
        return Result.success(sysConfigDetailVO);
    }

    @Override
    public Result editSysConfig(SysConfigDTO sysConfigDTO) {
        //参数校验
        Result result = sysConfigValidateService.validateSysConfigDTO(sysConfigDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //更新数据
        sysConfigService.setCustValueByCustCode(Constant.SysConfigCustCode.SUSPENDED_TERM,String.valueOf(sysConfigDTO.getSuspendedTerm()));
        sysConfigService.setCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_TERM,String.valueOf(sysConfigDTO.getReleaseTerm()));
        sysConfigService.setCustValueByCustCode(Constant.SysConfigCustCode.RELEASE_WAY,String.valueOf(sysConfigDTO.getReleaseWay()));
        //返回
        return Result.success();
    }

    @Override
    public Result getPartnershipConfig() {
        PartnershipConfigVO partnershipConfigVO = new PartnershipConfigVO();
        //获取合作企业性质
        String partnershipTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_TYPE);
        //获取合作企业项目性质
        String partnershipProjectTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.PARTNERSHIP_PROJECT_TYPE);
        //获取股票类型
        String stockTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.STOCK_TYPE);
        //获取负责人职务类型
        String directorPositionTypeStr
                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.DIRECTOR_POSITION_TYPE);
        //获取联系人职务类型
        String contactPositionTypeStr
                = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.CONTACT_POSITION_TYPE);
        //获取单位类型
        String enterpriseTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.ENTERPRISE_TYPE);
        //封装
        if(ObjectUtil.isNotEmpty(partnershipProjectTypeStr)){
            List<PartnershipTypeVO> partnershipTypeVOList = JSON.parseArray(partnershipTypeStr, PartnershipTypeVO.class);
            partnershipConfigVO.setPartnershipTypeVOList(partnershipTypeVOList);
        }
        if(ObjectUtil.isNotEmpty(partnershipProjectTypeStr)){
            List<PartnershipProjectTypeVO> partnershipProjectTypeVOList = JSON.parseArray(partnershipProjectTypeStr, PartnershipProjectTypeVO.class);
            partnershipConfigVO.setPartnershipProjectTypeVOList(partnershipProjectTypeVOList);
        }
        if(ObjectUtil.isNotEmpty(stockTypeStr)){
            List<StockTypeVO> stockTypeVOList = JSON.parseArray(stockTypeStr, StockTypeVO.class);
            partnershipConfigVO.setStockTypeVOList(stockTypeVOList);
        }
        if(ObjectUtil.isNotEmpty(directorPositionTypeStr)){
            List<PositionVO> directorPositionVOList = JSON.parseArray(directorPositionTypeStr, PositionVO.class);
            partnershipConfigVO.setDirectorPositionVOList(directorPositionVOList);
        }
        if(ObjectUtil.isNotEmpty(contactPositionTypeStr)){
            List<PositionVO> contactPositionVOList = JSON.parseArray(contactPositionTypeStr, PositionVO.class);
            partnershipConfigVO.setContactPositionVOList(contactPositionVOList);
        }
        if(ObjectUtil.isNotEmpty(enterpriseTypeStr)){
            List<EnterpriseTypeVO> enterpriseTypeVOList = JSON.parseArray(enterpriseTypeStr, EnterpriseTypeVO.class);
            partnershipConfigVO.setEnterpriseTypeVOList(enterpriseTypeVOList);
        }
        return Result.success(partnershipConfigVO);
    }

    @Override
    public Result getCaseType() {
        List<CaseTypeVO> caseTypeVOList = new ArrayList<CaseTypeVO>();
        String caseTypeStr = sysConfigService.getCustValueByCustCode(Constant.SysConfigCustCode.CASE_TYPE);
        if(ObjectUtil.isNotEmpty(caseTypeStr)){
            caseTypeVOList = JSON.parseArray(caseTypeStr, CaseTypeVO.class);
        }
        return Result.success(caseTypeVOList);
    }
}
