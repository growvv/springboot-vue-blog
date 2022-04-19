package com.rogn.springbootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rogn.springbootvue.mapper")
public class SpringbootVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootVueApplication.class, args);
    }

}
