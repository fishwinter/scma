package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.po.TItem;
import com.company.scma.mapper.ItemMapper;
import com.company.scma.service.mapperservice.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, TItem> implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public TItem getItemByItemCodeAndType(String itemCode, Integer type) {
        QueryWrapper<TItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.ITEM_CODE,itemCode);
        queryWrapper.eq(Constant.ColumnName.TYPE,type);
        List<TItem> tItemList = itemMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(tItemList)){
            return tItemList.get(0);
        }
        return null;
    }
}
