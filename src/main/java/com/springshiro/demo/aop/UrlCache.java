package com.springshiro.demo.aop;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


/**
 * @ProjectName: shiroDemo
 * @Package: com.springshiro.demo.aop
 * @ClassName: UrlCache
 * @Author: DELL
 * @Description: ${description}
 * @Date: 2019/12/25 18:46
 * @Version: 1.0
 */
@Configuration
public class UrlCache {
    @Bean
    public Cache<String, Integer> getCache() {
        // 缓存有效期为2秒
        return CacheBuilder.newBuilder().expireAfterWrite(2L, TimeUnit.SECONDS).build();
    }

}
