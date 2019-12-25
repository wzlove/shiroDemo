package com.springshiro.demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: shiroDemo
 * @Package: com.springshiro.demo.aop
 * @ClassName: NoRepeatSubmit
 * @Author: DELL
 * @Description: 防止重复提交标记注解
 * @Date: 2019/12/25 18:28
 * @Version: 1.0
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatSubmit {
}
