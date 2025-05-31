package olga.ailab.orderapi.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private String productId;
    private Integer quantity;
    private String userId;
}
