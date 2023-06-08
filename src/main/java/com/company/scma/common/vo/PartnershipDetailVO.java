package com.company.scma.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class PartnershipDetailVO {
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
     * 当前参与的活动
     */
    private OperationListRowVO myOperation;

    /**
     * 当前所有有效活动
     */
    private List<OperationListRowVO> allValidOperation;

    /**
     * 管理员账号
     */
    private String managerUsername;

    /**
     * 子账号数量
     */
    private Integer subAccountNum;
}
