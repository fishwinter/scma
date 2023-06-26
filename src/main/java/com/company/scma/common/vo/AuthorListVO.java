package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class AuthorListVO {
    /**
     * 作者总数
     */
    private Long AuthorTotal;
    /**
     * 作者信息列表
     */
    private List<AuthorListRowVO> authorListRowVOList;
}
