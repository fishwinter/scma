package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class OperateItemDto {
    /**
     * 数据id
     */
    private String itemId;

    /**
     * 0-否（撤回/撤销首页/撤销置顶/启用），1-是(发布/上首页/置顶/禁用)
     */
    private String operate;
}
