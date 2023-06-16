package com.company.scma.common.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_item
 * @author 
 */
@Data
@TableName("T_ITEM")
public class TItem implements Serializable {
    /**
     * 主体编码
     */
    private String itemCode;

    /**
     * 主体名称
     */
    private String itemName;

    /**
     * 父级编码
     */
    private String parentCode;

    /**
     * 主题类型：1-省，2-市
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}