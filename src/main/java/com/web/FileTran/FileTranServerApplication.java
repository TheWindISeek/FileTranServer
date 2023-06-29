package com.web.FileTran;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.web.FileTran.olddao"})
@ComponentScan(basePackages = {"com.*"})
public class FileTranServerApplication {
    /**
     * 后端入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(FileTranServerApplication.class, args);
    }
}
