package com.acui.merchant.web.config;

import com.acui.merchant.web.interceptor.AuthorizationInterceptor;
import com.acui.merchant.web.interceptor.MerchantInterceptor;
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
    public AuthorizationInterceptor authInterceptor() {
        return new AuthorizationInterceptor();
    }
    @Bean
    public MerchantInterceptor merchantInterceptor() {
        return new MerchantInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ai = registry.addInterceptor(authInterceptor());
        InterceptorRegistration mi = registry.addInterceptor(merchantInterceptor());
        // 配置拦截的路径
        ai.addPathPatterns("/**");
        mi.addPathPatterns("/**");
        // 配置不拦截的路径
        ai.excludePathPatterns("**/login");
        mi.excludePathPatterns("**/merchant");
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