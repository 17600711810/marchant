package com.acui.marchant.web.config;

import com.acui.marchant.web.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration 暂时不用
public class WebConfig implements WebMvcConfigurer {

    //将tokenInterceptor放入到spring容器中管理
    @Autowired
    public AuthorizationInterceptor authorizationInterceptor;

    /*实现此方法添加拦截器
     * addPathPatterns  拦截路径
     * excludePathPatterns() (不拦截的路径数组) 我这个demo没有使用
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截路径，表示此路径下的所有地址都会先执行此拦截器，通过之后才能访问Controller
        String[] addPathPatterns = {
                "/token/*"
        };
        registry.addInterceptor(authorizationInterceptor).addPathPatterns(addPathPatterns);
    }
}