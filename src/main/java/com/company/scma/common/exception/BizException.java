package com.company.scma.common.exception;

import com.company.scma.common.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
    
    public BizException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
        this.errorMsg = resultEnum.getMsg();
    }
}
