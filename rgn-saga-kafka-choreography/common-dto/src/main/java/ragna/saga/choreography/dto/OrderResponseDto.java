package ragna.saga.choreography.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ragna.saga.choreography.events.order.OrderStatus;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderResponseDto {
    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private Integer amount;
    private OrderStatus status;
}
