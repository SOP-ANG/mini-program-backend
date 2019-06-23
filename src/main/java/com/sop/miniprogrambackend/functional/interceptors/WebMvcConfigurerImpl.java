package com.sop.miniprogrambackend.functional.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册微信登录权限验证的拦截器
        InterceptorRegistration ir = registry.addInterceptor(new WxLoginInterceptor());
        // 拦截所有路径
        ir.addPathPatterns("/**");
        // 再添加不拦截路径
        ir.excludePathPatterns("/user/validateWxLogin");
    }
}
