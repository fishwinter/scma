package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserListVO {
    /**
     * 用户总数
     */
    private Long userTotal;
    /**
     * 用户信息
     */
    private List<UserListRowVO> userListRowVOList;
}
