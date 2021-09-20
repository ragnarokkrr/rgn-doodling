package ragna.saga.payment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ragna.saga.choreography.events.order.OrderEvent;
import ragna.saga.choreography.events.order.OrderStatus;
import ragna.saga.choreography.events.payment.PaymentEvent;
import ragna.saga.payment.service.PaymentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(final OrderEvent orderEvent) {
        if (orderEvent.getStatus().equals(OrderStatus.ORDER_CREATED)) {
            return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
        }

        return Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
    }
}
