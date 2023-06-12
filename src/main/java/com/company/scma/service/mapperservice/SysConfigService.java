package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TSysConfig;

public interface SysConfigService extends IService<TSysConfig> {
    public String getCustValueByCustCode(String custCode);
}
