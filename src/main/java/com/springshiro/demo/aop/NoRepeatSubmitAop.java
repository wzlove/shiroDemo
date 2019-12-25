package com.springshiro.demo.aop;


import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: shiroDemo
 * @Package: com.springshiro.demo.aop
 * @ClassName: NoRepeatSubmitAop
 * @Author: DELL
 * @Description: aop解析注释
 * @Date: 2019/12/25 18:30
 * @Version: 1.0
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Slf4j
public class NoRepeatSubmitAop {
    private static final String JWT_TOKEN_KEY = "jwt-token";

    @Autowired
    private Cache<String, Integer> cache;


    @Pointcut("@annotation(com.springshiro.demo.aop.NoRepeatSubmit)")
    public void serviceNoRepeat() {
    }

    @Around("serviceNoRepeat()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("dd");
        HttpServletRequest request = RequestHolder.getCurrentRequest();
//        String jwtToken = request.getHeader(JWT_TOKEN_KEY);
        String jwtToken = JWT_TOKEN_KEY;
//        String key = jwtToken + "-" + request.getServletPath()+JSON.toJSONString(request.getParameterMap());
        String key = jwtToken+"-";
        if (cache.getIfPresent(key) == null) {
            Object o = pjp.proceed();
            // 2秒内统一用户同一个地址同一个参数，视为重复提交
            cache.put(key, 0);
            return o;
        } else {
            log.error("重复提交");
            throw new Exception("重复提交");
        }
    }

}
