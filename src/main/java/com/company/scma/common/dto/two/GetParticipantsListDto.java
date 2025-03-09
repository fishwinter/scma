package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class GetParticipantsListDto {
    /**
     * 姓名
     */
    private String name;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
