package com.company.scma.common.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PartnershipDetailVO {
    /**
     * 单位名称
     */
    private String partnershipName;

    /**
     * 当前合作企业的企业性质，1-国企，2-民企，3-外企
     */
    private PartnershipTypeVO myPartnershipType;

    /**
     * 所有合作企业性质
     */
    private List<PartnershipTypeVO> allPartnershipType;

    /**
     * 企业logo url
     */
    private String logoUrl;

    /**
     * 地址
     */
    private String addr;

    /**
     * 企业邮箱
     */
    private String email;

    /**
     * 官方网址
     */
    private String webAddr;

    /**
     * 微信公众号
     */
    private String wechatAccount;

    /**
     * 视频号
     */
    private String videoAccount;

    /**
     * 负责人姓名
     */
    private String directorName;

    /**
     * 所有职务
     */
    private List<PositionVO> allPosition;

    /**
     * 负责人职务
     */
    private String directorPosition;

    /**
     * 负责人手机号
     */
    private String directorTel;

    /**
     * 负责人微信号
     */
    private String directorWechat;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人职务
     */
    private PositionVO contactPosition;

    /**
     * 联系人手机
     */
    private String contactTel;

    /**
     * 联系人微信号
     */
    private String contactWechat;

    /**
     * 协办金额
     */
    private String amount;

    /**
     * 进账时间
     */
    private Date paymentTime;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 合同开始时间
     */
    private Date contractStartDate;

    /**
     * 合同结束时间
     */
    private Date contractEndDate;

    /**
     * 当前合作企业合作项目类型，1-协办，2-广告，3-活动
     */
    private List<PartnershipProjectTypeVO> myProjectType;

    /**
     * 所有合作企业合作项目类型，1-协办，2-广告，3-活动
     */
    private List<PartnershipProjectTypeVO> allProjectType;

    /**
     * 首次加入协办单位时间
     */
    private Date firstJoinTime;

    /**
     * 对接服务人员
     */
    private String serviceProvider;

    /**
     * 备注
     */
    private String remark;

    /**
     * 当前参与的活动
     */
    private OperationListRowVO myOperation;

    /**
     * 所有合法的活动
     */
    private List<OperationListRowVO> allOperation;

    /**
     * 是否上市 0-否。1-是
     */
    private Integer isListed;

    /**
     * 当前股票类型
     */
    private StockTypeVO myStockType;

    /**
     * 所有股票类型
     */
    private List<StockTypeVO> allStockType;

    /**
     * 当前绑定活动状态：0-未开始，1-正常，2-已结束
     */
    private Integer operationStatus;

    /**
     * 管理员username
     */
    private String username;

    /**
     * 子账号数量
     */
    private Integer subAccountNum;
}
