package com.example.ezul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EzulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzulApplication.class, args);
    }

}
