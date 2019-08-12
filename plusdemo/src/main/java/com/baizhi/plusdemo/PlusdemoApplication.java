package com.baizhi.plusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class PlusdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlusdemoApplication.class, args);
    }

}
