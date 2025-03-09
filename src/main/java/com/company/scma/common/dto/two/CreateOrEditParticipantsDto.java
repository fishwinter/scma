package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class CreateOrEditParticipantsDto {
    /**
     * 参会者id，编辑时传入
     */
    private String participantsId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别,0-女，1-男
     */
    private String gender;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 所属单位
     */
    private String organization;

    /**
     * 职务
     */
    private String position;

    /**
     * 电子邮箱
     */
    private String email;
}
