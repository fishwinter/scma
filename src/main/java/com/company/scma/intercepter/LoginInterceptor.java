package com.company.scma.intercepter;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.exception.BizException;
import com.company.scma.common.po.TUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger("userRequest");
    
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute(Constant.Common.SESSION_KEY);
        
        //如果session中username为空则抛出异常，全局异常处理器返回未登录提示
        if(StringUtils.isEmpty(username)){
            throw new BizException(ResultEnum.NO_LOGIN);
        }
        
        //单独打印请求日志
        TUser tUser = (TUser) SecurityUtils.getSubject().getPrincipal();
        if(ObjectUtil.isNotEmpty(tUser)){
            logger.info("userid[{}],requestUrl[{}]",tUser.getUserid(),request.getRequestURL());
        }
        return true;
    }
}
