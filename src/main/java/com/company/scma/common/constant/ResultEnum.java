package com.company.scma.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {
    SUCCESS(200,"success"),
    
    //用户登录错误
    ERROR_USERNAME_OR_PASSWORD(401,"用户名或密码错误"),
    NO_LOGIN(402,"用户未登录"),
    ERROR_PARAM(403,"参数错误"),
    NO_PERMISSION(405,"没有权限"),
    
    //角色模块错误
    
    
    //其他错误
    UNKNOWN_ERROR(500,"未知错误");
    ;
    
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
