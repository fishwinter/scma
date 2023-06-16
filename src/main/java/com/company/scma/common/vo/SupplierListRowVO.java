package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierListRowVO {
    /**
     * 供应商id
     */
    private Integer supplierId;

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
     * 创建时间
     */
    private Date buildDate;
}
