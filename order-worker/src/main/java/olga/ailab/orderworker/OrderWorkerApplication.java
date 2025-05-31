package olga.ailab.orderworker;

import olga.ailab.common.config.RabbitOrderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "olga.ailab.orderworker",
    "olga.ailab.common"
})
@EntityScan(basePackages = {
    "olga.ailab.orderworker.entity",
    "olga.ailab.common.entity"
})
@EnableMongoRepositories(basePackages = {
    "olga.ailab.orderworker.repository",
    "olga.ailab.common.repository"
})
@EnableConfigurationProperties(RabbitOrderProperties.class)
public class OrderWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderWorkerApplication.class, args);
    }
} 