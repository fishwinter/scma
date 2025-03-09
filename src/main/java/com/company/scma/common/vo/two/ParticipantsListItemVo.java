package com.company.scma.common.vo.two;

import lombok.Data;

@Data
public class ParticipantsListItemVo {
    /**
     * 参会者id
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
     * 所属单位
     */
    private String organization;

    /**
     * 职务
     */
    private String position;
}
