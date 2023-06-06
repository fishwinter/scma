package com.company.scma.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GetUserDTO {
    private String username;
    private String name;
    private String tel;
    private Date startDate;
    private Date endDate;
    @NotNull
    private Long currentPage;
    @NotNull
    private Long pageSize;
}
