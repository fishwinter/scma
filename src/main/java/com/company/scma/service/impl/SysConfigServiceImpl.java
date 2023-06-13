package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TSysConfig;
import com.company.scma.mapper.SysConfigMapper;
import com.company.scma.service.mapperservice.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, TSysConfig> implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;
    @Override
    public String getCustValueByCustCode(String custCode) {
        QueryWrapper<TSysConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.CUSTCODE,custCode);
        List<TSysConfig> tSysConfigList = sysConfigMapper.selectList(queryWrapper);
        if(ObjectUtil.isEmpty(tSysConfigList)){
            return tSysConfigList.get(0).getCustValue();
        }
        return null;
    }

    @Override
    public void updateCustValueByCustCode(String custCode, String custValue) {
        UpdateWrapper<TSysConfig> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.CUSTVALUE,custValue);
        updateWrapper.eq(Constant.ColumnName.CUSTCODE,custCode);
        sysConfigMapper.update(null,updateWrapper);
    }

}
