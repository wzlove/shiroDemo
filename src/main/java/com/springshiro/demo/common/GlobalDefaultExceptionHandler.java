package com.springshiro.demo.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
  * 1、新建一个Class,这里取名为GlobalDefaultExceptionHandler
  * 2、在class上添加注解，@ControllerAdvice;
  * 3、在class中添加一个方法
  * 4、在方法上添加@ExcetionHandler拦截相应的异常信息；
  * 5、如果返回的是View -- 方法的返回值是ModelAndView;
  * 6、如果返回的是String或者是Json数据，那么需要在方法上添加@ResponseBody注解.
  * 
  * 
  * @author gc
  * @version v.0.1
  * @date 2018年8月18日
  */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(DisabledAccountException.class)
    @ResponseBody
    public Object defaultExceptionHandler(HttpServletRequest req, Exception e) {
        Map<String,String> map=new HashMap<>();
        map.put("result","对不起，你没有访问权限！");
        return map;
    }

  /*  @ExceptionHandler(DisabledAccountException.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest req, Exception e) {
        return "对不起，你没有访问权限！";
    }*/
}