package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.scma.common.po.TItem;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.ItemVO;
import com.company.scma.common.vo.Result;
import com.company.scma.service.bizservice.ItemBizService;
import com.company.scma.service.mapperservice.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemBizServiceImpl implements ItemBizService {
    @Autowired
    private ItemService itemService;
    
    @Override
    public Result getAllItem() {
        List<TItem> tItemList = itemService.getAllItem();
        List<ItemVO> itemVOList = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(tItemList)){
            itemVOList = GenerateUtil.getItemVOList(tItemList);
        }
        return Result.success(itemVOList);
    }
}
