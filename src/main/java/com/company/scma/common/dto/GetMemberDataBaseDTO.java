package com.company.scma.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GetMemberDataBaseDTO {
    /**
     * 会员名称
     */
    String memberName;
    /**
     * 所属单位
     */
    String partnershipName;
    /**
     * 所属人名称
     */
    String username;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date endDate;
    @NotNull
    private Long currentPage;
    @NotNull
    private Long pageSize;
}
