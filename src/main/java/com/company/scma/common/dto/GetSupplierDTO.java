package com.company.scma.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class GetSupplierDTO {
    /**
     * 供应商名称
     */
    private String supplierName;
    
    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 合作项目，1-协办，2-广告，3-活动
     */
    private List<Integer> projectType;

    /**
     * 对接服务人员
     */
    private String serviceProvider;
    
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
