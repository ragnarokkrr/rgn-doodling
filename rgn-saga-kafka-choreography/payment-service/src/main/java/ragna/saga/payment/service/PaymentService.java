package ragna.saga.payment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ragna.saga.choreography.events.order.OrderEvent;
import ragna.saga.choreography.events.payment.PaymentEvent;

@Service
@Slf4j
public class PaymentService {

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        return null;
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {

    }
}
