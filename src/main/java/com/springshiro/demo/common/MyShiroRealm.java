package com.springshiro.demo.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @ProjectName: shiroDemo
 * @Package: com.springshiro.demo.common
 * @ClassName: MyShiroRealm
 * @Author: DELL
 * @Description: ${description}
 * @Date: 2019/12/17 18:14
 * @Version: 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * @Description 授权
     * @Create 2019/12/17 18:15
     * @Author wangzheng
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = (SimpleAuthorizationInfo) SecurityUtils.getSubject().getSession().getAttribute("simpleAuthorizationInfo");

        return authorizationInfo;
    }


    /**
     * @Description 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @Create 2019/12/17 18:15
     * @Author wangzheng
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        if(name.equals(password)){
            return new SimpleAuthenticationInfo(new User(), password, getName());
        }else {
            System.out.println("认证失败");
            throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        }

    }
}
