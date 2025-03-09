package com.company.scma.common.vo.two;

import lombok.Data;

/**
 * 酒店统计对象
 */
@Data
public class HotelCountVo {
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

    /**
     * 剩余(套)
     */
    private String remain;
}