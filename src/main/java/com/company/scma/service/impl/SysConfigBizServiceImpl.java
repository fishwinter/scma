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
        //封装
        if(ObjectUtil.isNotEmpty(partnershipProjectTypeStr)){
            List<PartnershipTypeVO> partnershipTypeVOList = JSON.parseArray(partnershipTypeStr, PartnershipTypeVO.class);
            partnershipConfigVO.setPartnershipTypeVOList(partnershipTypeVOList);
        }
        if(ObjectUtil.isNotEmpty(partnershipProjectTypeStr)){
            List<PartnershipProjectTypeVO> partnershipProjectTypeVOList = JSON.parseArray(partnershipProjectTypeStr, PartnershipProjectTypeVO.class);
            partnershipConfigVO.setPartnershipProjectTypeVOList(partnershipProjectTypeVOList);
        }
        return Result.success(partnershipConfigVO);
    }
}
