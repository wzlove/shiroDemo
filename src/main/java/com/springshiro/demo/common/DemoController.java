package com.springshiro.demo.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ProjectName: shiroDemo
 * @Package: com.springshiro.demo.common
 * @ClassName: DemoController
 * @Author: DELL
 * @Description: ${description}
 * @Date: 2019/12/17 18:33
 * @Version: 1.0
 */
@Controller
public class DemoController {
    //跳转到登录表单页面
    @RequestMapping(value="login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        String username=request.getParameter("username")+"";
        String password="456";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        return "666";
    }

    /**
     * ajax登录请求
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="ajaxLogin",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitLogin(String username, String password, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            Session session = SecurityUtils.getSubject().getSession();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value="index")
    @ResponseBody
    public String index() {
        return "index";
    }

    //未授权跳转的页面
    @RequestMapping(value="4030")
    public String noPermissions() {
        return "403.html";
    }
}
