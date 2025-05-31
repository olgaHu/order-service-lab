package olga.ailab.common.message.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderId;
    private String userId;
    private String productId;
    private Integer quantity;
    private LocalDateTime createAt;
}
