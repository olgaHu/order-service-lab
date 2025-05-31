package olga.ailab.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "rabbitmq.order")
public class RabbitOrderProperties {
    private String exchange;
    private String queue;
    private String routingKey;
}