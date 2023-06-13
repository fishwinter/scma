package com.company.scma.service.impl;

import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.SysConfigDTO;
import com.company.scma.common.po.TMemberType;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.SysConfigDetailVO;
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
        //封装
        sysConfigDetailVO.setSuspendedTerm(Integer.valueOf(suspendedTerm));
        sysConfigDetailVO.setReleaseTerm(Integer.valueOf(releaseTerm));
        sysConfigDetailVO.setReleaseWay(Integer.valueOf(releaseWay));
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
        sysConfigService.updateCustValueByCustCode(String.valueOf(sysConfigDTO.getSuspendedTerm()),Constant.SysConfigCustCode.SUSPENDED_TERM);
        sysConfigService.updateCustValueByCustCode(String.valueOf(sysConfigDTO.getReleaseTerm()),Constant.SysConfigCustCode.RELEASE_TERM);
        sysConfigService.updateCustValueByCustCode(String.valueOf(sysConfigDTO.getReleaseWay()),Constant.SysConfigCustCode.RELEASE_WAY);
        //返回
        return Result.success();
    }
}
