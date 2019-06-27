package com.sop.miniprogrambackend.functional.conf;

import com.sop.miniprogrambackend.functional.interceptors.WxLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    @Autowired
    private MiniProgramBackendConf conf;

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

    /**
     * 添加静态资源文件虚拟访问路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                this.conf.getRelative_record_path() + "**"
        ).addResourceLocations("file:" + this.conf.getRecord_path() + "/");
        registry.addResourceHandler(
                this.conf.getRelative_img_path() + "**"
        ).addResourceLocations("file:" + this.conf.getImg_path() + "/");
    }
}
