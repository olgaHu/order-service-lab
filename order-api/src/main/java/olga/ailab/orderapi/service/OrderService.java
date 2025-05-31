package olga.ailab.orderapi.service;

import lombok.RequiredArgsConstructor;
import olga.ailab.common.message.order.OrderMessage;
import olga.ailab.orderapi.dto.OrderDTO;
import olga.ailab.orderapi.entity.OrderEntity;
import olga.ailab.orderapi.mapper.OrderMapper;
import olga.ailab.orderapi.messaging.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;

    public OrderEntity createOrder(OrderDTO orderDTO) {
        OrderEntity order = orderMapper.toEntity(orderDTO);
        order.setOrderId(generateOrderId());
        order.setCreatedAt(LocalDateTime.now());

        //TODO : 延伸：寫入 Redis/MongoDB，或轉發到 MQ

        // ✅ 轉成 MQ 訊息格式，發送到 RabbitMQ
        OrderMessage message = OrderMessage.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .createAt(order.getCreatedAt())
                .build();

        orderProducer.sendOrder(message);

        return order;
    }

    private String generateOrderId() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
