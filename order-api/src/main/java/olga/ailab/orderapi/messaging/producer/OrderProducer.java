package olga.ailab.orderapi.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import olga.ailab.common.config.RabbitOrderProperties;
import olga.ailab.common.message.order.OrderMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitOrderProperties rabbitOrderProperties;

    public void sendOrder(OrderMessage orderMessage) {
        rabbitTemplate.convertAndSend(
                rabbitOrderProperties.getExchange(),
                rabbitOrderProperties.getRoutingKey(),
                orderMessage
        );
        log.info("Order sent to MQ: {}", orderMessage);
    }
}
