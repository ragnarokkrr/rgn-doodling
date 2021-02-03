package ragna.saga.choreography.events.payment;

import lombok.Data;
import lombok.NoArgsConstructor;
import ragna.saga.choreography.dto.PaymentDto;
import ragna.saga.choreography.events.Event;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PaymentEvent implements Event {
    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private PaymentDto payment;
    private PaymentStatus status;

    public PaymentEvent(PaymentDto payment, PaymentStatus status) {
        this.payment = payment;
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
