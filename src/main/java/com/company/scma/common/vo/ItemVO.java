package com.company.scma.common.vo;

import lombok.Data;

@Data
public class ItemVO {
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
}
