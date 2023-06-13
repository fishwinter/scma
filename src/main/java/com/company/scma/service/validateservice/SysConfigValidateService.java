package com.company.scma.service.validateservice;

import com.company.scma.common.dto.SysConfigDTO;
import com.company.scma.common.vo.Result;

public interface SysConfigValidateService {
    public Result validateSysConfigDTO(SysConfigDTO sysConfigDTO);
}
