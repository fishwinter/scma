package com.company.scma.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GetOperationDTO {
    /**
     * 活动名称
     */
    private String operationName;

    /**
     * 活动负责人
     */
    private String director;

    /**
     * 联系电话
     */
    private String contactTel;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date endDate;
    @NotNull
    private Long currentPage;
    @NotNull
    private Long pageSize;
}
