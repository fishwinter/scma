package com.company.scma.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GetPartnershipDTO {
    /**
     * 单位名称
     */
    private String partnershipName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机
     */
    private String contactTel;

    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date startDate;
    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date endDate;
    /**
     * 当前页数
     */
    @NotNull
    private Long currentPage;
    /**
     * 每页容量
     */
    @NotNull
    private Long pageSize;
}
