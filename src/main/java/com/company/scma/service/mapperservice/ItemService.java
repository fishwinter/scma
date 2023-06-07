package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.po.TItem;

import java.util.List;

public interface ItemService extends IService<TItem> {
    public TItem getItemByItemCodeAndType(String itemCode, Integer type);
    
    public List<TItem> getAllItem();
}