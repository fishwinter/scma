package com.company.scma.intercepter;

import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute(Constant.Common.SESSION_KEY);
        
        //如果session中username为空则抛出异常，全局异常处理器返回未登录提示
        if(StringUtils.isEmpty(username)){
            throw new BizException(ResultEnum.NO_LOGIN);
        }
        return true;
    }
}
