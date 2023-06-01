package com.company.scma.common.exception;

import com.company.scma.common.constant.ResultEnum;

public class BizException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;
    
    public BizException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
        this.errorMsg = resultEnum.getMsg();
    }
}
