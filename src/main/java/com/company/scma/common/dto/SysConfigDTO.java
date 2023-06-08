package com.company.scma.common.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class SysConfigDTO {
    /**
     *活动过期后账号冻结时间
     */
    @NonNull
    Integer suspendedTerm;
    /**
     *账号冻结后释放时间
     */
    @NonNull
    Integer releaseTerm;
    /**
     *账号释放方式，1-释放到公司会员库，2-释放为公共资源
     */
    @NonNull
    Integer releaseWay;
}
