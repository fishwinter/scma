package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.SysConfigDTO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.SysConfigValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigValidateServiceImpl implements SysConfigValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    @Override
    public Result validateSysConfigDTO(SysConfigDTO sysConfigDTO) {
        if(ObjectUtil.isEmpty(sysConfigDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(!commonValidateService.validateAnnotation(sysConfigDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        return Result.success();
    }
}
