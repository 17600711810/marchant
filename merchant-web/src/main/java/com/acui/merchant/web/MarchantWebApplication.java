package com.acui.merchant.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.acui")
public class MarchantWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarchantWebApplication.class, args);
    }

}
