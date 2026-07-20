package com.sparta.msa.point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.sparta.msa.point", "com.sparta.msa.common"})
@EnableFeignClients
public class PointServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PointServiceApplication.class, args);
    }
}
