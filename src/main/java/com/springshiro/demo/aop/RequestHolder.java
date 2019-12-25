package com.springshiro.demo.aop;

import com.springshiro.demo.common.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: shiroDemo
 * @Package: com.springshiro.demo.aop
 * @ClassName: RequestHolder
 * @Author: DELL
 * @Description: ${description}
 * @Date: 2019/12/25 18:37
 * @Version: 1.0
 */
public class RequestHolder {
    /**
     * 处理高并发的对象
     * 1. 每个线程是独立的
     * 2. 登陆的时候会把每个用户的信息和请求的信息放入线程里面
     * 3. 而在以后取用户信息可以直接取这里取
     */
    private static final ThreadLocal<User> userHolder = new ThreadLocal<User>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(User sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        System.out.println("66666666666666666666");
        requestHolder.set(request);
    }

    public static User getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}
