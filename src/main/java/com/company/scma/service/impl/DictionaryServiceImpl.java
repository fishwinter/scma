package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TDictionary;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.mapper.DictionaryMapper;
import com.company.scma.service.mapperservice.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, TDictionary> implements DictionaryService{
    @Autowired
    private DictionaryMapper dictionaryMapper;
    
    @Override
    public Integer insertByDicType(String dicName, Integer dicType) {
        if(ObjectUtil.isEmpty(dicName) || ObjectUtil.isEmpty(dicType)){
            return null;
        }
        TDictionary tDictionary = GenerateUtil.getTDictionary(dicName, dicType);
        dictionaryMapper.insert(tDictionary);
        return tDictionary.getDicId();
    }

    @Override
    public List<TDictionary> selectTDictionaryByDicType(Integer dicType) {
        if(ObjectUtil.isEmpty(dicType)){
            return null;
        }
        QueryWrapper<TDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.DIC_TYPE,dicType);
        List<TDictionary> tDictionaryList = dictionaryMapper.selectList(queryWrapper);
        return tDictionaryList;
    }
}
