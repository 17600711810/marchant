package com.acui.marchant.web.config;

import com.acui.marchant.web.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    //关键，将拦截器作为bean写入配置中
    @Bean
    public AuthorizationInterceptor myAuthInterceptor() {
        return new AuthorizationInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(myAuthInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
         ir.excludePathPatterns("**/login");
        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }
}