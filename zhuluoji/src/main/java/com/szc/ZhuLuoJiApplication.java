package com.szc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.szc.mapper")
@SpringBootApplication
public class ZhuLuoJiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhuLuoJiApplication.class,args);
    }
}