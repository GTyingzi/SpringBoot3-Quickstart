package com.yingzi.interceptor.component;

import com.yingzi.interceptor.annotation.NoNeedToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;

/**
 * @author yingzi
 * @date 2025/3/10:20:07
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器tokenInterceptor：进入拦截器tokenInterceptor");

        // 获取方法上的NoNeedToken注解，若有则放行
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Annotation annotation = handler.getClass().getAnnotation(NoNeedToken.class);
        if(handlerMethod.getMethodAnnotation(NoNeedToken.class) != null || handlerMethod.getBeanType().isAnnotationPresent(NoNeedToken.class)){
            log.info("拥有NoNeedToken注解，放行");
            return true;
        }

        // 若无NoNeedToken注解，则判断token是否有效
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            response.getWriter().write("token is null");
            return false;
        }
        if (!TOKEN.equals(token)) {
            response.getWriter().write("token is error");
            return false;
        }
        // true为放行，false为拦截
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器tokenInterceptor：controller执行完毕");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("拦截器tokenInterceptor：返回前端之前");
    }
}
