package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GetMyMemberDTO {
    String memberName;
    String contacts;
    String tel;
    Date startDate;
    Date endDate;
    @NotNull
    private Long currentPage;
    @NotNull
    private Long pageSize;
}
