package olga.ailab.orderapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderEntity {
    private String orderId;
    private String productId;
    private Integer quantity;
    private String userId;
    private LocalDateTime createdAt;
}
