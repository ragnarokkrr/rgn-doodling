package ragna.command.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ragna.command.mapper.CommandMapper;
import ragna.cqrs.command.inbound.model.ReceivedOrder;
import ragna.cqrs.command.model.Order;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@RestController
public class CommandController {
  // TODO: Reimplement using Sinks
  private final EmitterProcessor<Message<ReceivedOrder>> messageEmitterProcessor =
      EmitterProcessor.create();

  @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Order> createOrder(@RequestBody @NotNull Order order) {

    Assert.notNull(order, "Order should not be null.");
    final var orderId =
        Optional.of(order).map(Order::getOrderId).orElseGet(() -> UUID.randomUUID().toString());
    order.setOrderId(orderId);

    final var receivedOrderMessage =
        Mappers.getMapper(CommandMapper.class).getReceivedOrderMessage(order);
    receivedOrderMessage.setOrderId(orderId);
    receivedOrderMessage.setTsReceived(Instant.now().toEpochMilli());

    messageEmitterProcessor.onNext(
        MessageBuilder.withPayload(receivedOrderMessage)
            .setHeader(KafkaHeaders.MESSAGE_KEY, receivedOrderMessage.getCustomerId())
            .build());

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(order);
  }

  @Bean
  public Supplier<Flux<Message<ReceivedOrder>>> orderSupplier() {
    return () -> messageEmitterProcessor;
  }
}
