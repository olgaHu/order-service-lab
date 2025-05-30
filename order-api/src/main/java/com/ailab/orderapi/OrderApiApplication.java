package com.ailab.orderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.ailab.orderapi",
    "com.ailab.common"
})
@EntityScan(basePackages = {
    "com.ailab.orderapi.entity",
    "com.ailab.common.entity"
})
@EnableMongoRepositories(basePackages = {
    "com.ailab.orderapi.repository",
    "com.ailab.common.repository"
})
public class OrderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApiApplication.class, args);
    }
} 