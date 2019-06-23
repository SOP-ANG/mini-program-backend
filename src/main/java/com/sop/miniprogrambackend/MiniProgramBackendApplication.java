package com.sop.miniprogrambackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sop.miniprogrambackend"})
@MapperScan("com.sop.miniprogrambackend.model")
public class MiniProgramBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProgramBackendApplication.class, args);
    }

}
