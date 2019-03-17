package com.acui.merchant.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages="com.acui")
public class MerchantMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchantMapperApplication.class, args);
    }

}
