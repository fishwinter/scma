package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class ColumnListItemVO {
    /**
     * 总数
     */
    private String total;

    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 上级栏目名称
     */
    private String parentName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 创建时间,yyyy-mm-dd格式
     */
    private String createTime;
}