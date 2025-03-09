package com.company.scma.common.vo.two;

import lombok.Data;

import java.util.List;

@Data
public class ColumnTreeItemVo {
    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 上级栏目 ID
     */
    private String parentId;

    /**
     * 上级栏目名称
     */
    private String parentName;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 子栏目列表
     */
    private List<ColumnTreeItemVo> childColumnList;
}
