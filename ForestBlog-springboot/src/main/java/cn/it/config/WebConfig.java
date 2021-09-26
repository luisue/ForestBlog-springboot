package cn.it.config;

import cn.it.interceptor.AuthenticationInterceptor;
import cn.it.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author kylin
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器路径，拦截servlet请求
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/admin").addPathPatterns("/admin/**");
        //Token拦截验证,先拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
