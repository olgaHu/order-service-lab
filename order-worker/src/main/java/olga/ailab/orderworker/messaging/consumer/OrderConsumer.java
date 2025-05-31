package olga.ailab.orderworker.messaging.consumer;

import olga.ailab.common.message.order.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    @RabbitListener(queues = "${rabbitmq.order.queue}")
    public void receiveOrder(OrderMessage orderMessage) {
        log.info("Received order from MQ: {}", orderMessage);
        // 處理訂單邏輯 : 儲存 DB
    }
}