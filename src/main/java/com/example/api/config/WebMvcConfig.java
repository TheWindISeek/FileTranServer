package com.example.api.config;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 资源访问拦截器
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //super.addResourceHandlers(registry);
        //如果别人的请求符合 /static/** 那么就将这个请求重定向到 classpath: /static/ 下
        System.out.println("拦截访问器");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
