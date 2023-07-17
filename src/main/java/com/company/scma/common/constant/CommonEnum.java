package com.company.scma.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CommonEnum {
    //是否
    JUDGE_YES(1,"是"),
    JUDGE_NO(0,"否")
    ;
    
    private Integer code;
    private String msg;
    
    public static String getMsgByCode(Integer code){
        for (CommonEnum commonEnum : CommonEnum.values()) {
            if(commonEnum.getCode() == code){
                return commonEnum.getMsg();
            }
        }
        return null;
    }
}
