package com.web.FileTran.config;

import com.web.FileTran.listener.UserSessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 资源访问拦截器
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //super.addResourceHandlers(registry);
        //如果别人的请求符合 /static/** 那么就将这个请求重定向到 classpath: /static/ 下
        System.out.println("拦截访问器");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public ServletListenerRegistrationBean<UserSessionListener> sessionListenerRegistration() {
        System.out.println("session映射监听器");
        ServletListenerRegistrationBean<UserSessionListener> registration = new ServletListenerRegistrationBean<>();
        registration.setListener(new UserSessionListener());
        return registration;
    }
}
