package ragna.saga.choreography.events.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import ragna.saga.choreography.dto.PurchaseOrderDto;
import ragna.saga.choreography.events.Event;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderEvent implements Event {
    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private PurchaseOrderDto purchaseOrder;
    private OrderStatus status;

    public OrderEvent(PurchaseOrderDto purchaseOrder, OrderStatus status) {
        this.purchaseOrder = purchaseOrder;
        this.status = status;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
