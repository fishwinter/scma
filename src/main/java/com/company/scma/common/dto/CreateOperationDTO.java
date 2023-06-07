package com.company.scma.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateOperationDTO {
    /**
     * 活动名称
     */
    @NotBlank
    private String operationName;

    /**
     * 活动负责人
     */
    private String director;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 活动开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @NotNull
    private Date startDate;

    /**
     * 活动结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @NotNull
    private Date endDate;

    /**
     * 简介
     */
    private String introduction;
}
