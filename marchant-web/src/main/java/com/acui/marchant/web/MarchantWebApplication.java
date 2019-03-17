package com.acui.marchant.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.acui")
public class MarchantWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarchantWebApplication.class, args);
    }

}
