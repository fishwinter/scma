package com.company.scma.service.bizservice;

import com.company.scma.common.vo.Result;
import com.company.scma.common.dto.SysConfigDTO;

public interface SysConfigBizService {
    public Result getSysConfig();
    public Result editSysConfig(SysConfigDTO sysConfigDTO);
    public Result getPartnershipConfig();
}
