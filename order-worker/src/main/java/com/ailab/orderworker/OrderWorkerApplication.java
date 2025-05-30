package com.ailab.orderworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.ailab.orderworker",
    "com.ailab.common"
})
@EntityScan(basePackages = {
    "com.ailab.orderworker.entity",
    "com.ailab.common.entity"
})
@EnableMongoRepositories(basePackages = {
    "com.ailab.orderworker.repository",
    "com.ailab.common.repository"
})
public class OrderWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderWorkerApplication.class, args);
    }
} 