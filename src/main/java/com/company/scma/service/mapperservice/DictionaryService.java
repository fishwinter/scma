package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TDictionary;

import java.util.List;

public interface DictionaryService extends IService<TDictionary> {
    public Integer insertByDicType(String dicName,Integer dicType);
    
    public List<TDictionary> selectTDictionaryByDicType(Integer dicType);
}
