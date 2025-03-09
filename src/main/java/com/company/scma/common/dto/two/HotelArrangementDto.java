package com.company.scma.common.dto.two;

import lombok.Data;

/**
 * 酒店安排对象
 */
@Data
public class HotelArrangementDto {
    /**
     * 时间
     */
    private String date;

    /**
     * 酒店
     */
    private String hotelName;

    /**
     * 标准
     */
    private String standard;

    /**
     * 数量（套）
     */
    private String quantity;
}