package com.example.crmlsr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.crmlsr.mapper")
public class CrmlsrApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmlsrApplication.class, args);
    }

}
