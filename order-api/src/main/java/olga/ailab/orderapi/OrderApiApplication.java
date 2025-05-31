package olga.ailab.orderapi;

import olga.ailab.common.config.RabbitOrderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "olga.ailab.orderapi",
    "olga.ailab.common"
})
@EntityScan(basePackages = {
    "olga.ailab.orderapi.entity",
    "olga.ailab.common.entity"
})
@EnableMongoRepositories(basePackages = {
    "olga.ailab.orderapi.repository",
    "olga.ailab.common.repository"
})
@EnableConfigurationProperties(RabbitOrderProperties.class)
public class OrderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApiApplication.class, args);
    }
} 