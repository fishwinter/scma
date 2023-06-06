package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserListVO {
    private Long userTotal;
    private List<UserListRowVO> userListRowVOList;
}
