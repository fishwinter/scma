package com.company.scma.handler;

import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.exception.BizException;
import com.company.scma.common.vo.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理自定义的登录异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result bizExceptionHandler(BizException e){
        return Result.getResult(ResultEnum.NO_LOGIN);
    }

    /**
     * 处理权限异常
     */
    @ExceptionHandler(value = {UnauthorizedException.class, AuthorizationException.class})
    @ResponseBody
    public Result unauthorizedExceptionHandler(AuthorizationException e){
        return Result.getResult(ResultEnum.NO_PERMISSION);
    }

    /**
     * 处理其他异常
     * @param e
     * @returnf
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result ExceptionHandler(Exception e){
        logger.info("系统发生错误，错误信息{}",e.getMessage());
        logger.error(e.getMessage(),e);
        return Result.getResult(ResultEnum.UNKNOWN_ERROR);
    }
}
