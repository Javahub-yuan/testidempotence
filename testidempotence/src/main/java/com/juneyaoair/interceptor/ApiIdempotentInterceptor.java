package com.juneyaoair.interceptor;

import com.juneyaoair.annotation.ApiIdempotent;
import com.juneyaoair.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 接口幂等性拦截器
 */
public class ApiIdempotentInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(ApiIdempotentInterceptor.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入拦截器内");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        logger.info("methodAnnotation = {}",methodAnnotation);
        if (methodAnnotation != null) {
            String parameter = request.getParameter("token");
            if (parameter != null || parameter.length() != 0) {
                String checkToken = tokenService.checkToken(parameter);
                if (!"success".equalsIgnoreCase(checkToken)) {
                    throw new RuntimeException("已处理，请"+checkToken+"秒后重试");
                }
            } else {
                    throw new RuntimeException("token不能为空");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
