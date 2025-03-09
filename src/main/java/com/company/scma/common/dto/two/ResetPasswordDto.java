package com.company.scma.common.dto.two;

import lombok.Data;

@Data
public class ResetPasswordDto {
    /**
     * 会员id
     */
    private String memberId;

    /**
     * 老密碼
     */
    private String oldPassword;

    /**
     * 新密碼
     */
    private String newPassword;
}
