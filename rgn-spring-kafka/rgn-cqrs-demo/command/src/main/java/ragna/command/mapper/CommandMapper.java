package ragna.command.mapper;

import org.mapstruct.Mapper;
import ragna.cqrs.command.inbound.model.ReceivedOrder;
import ragna.cqrs.command.model.Order;
import ragna.cqrs.command.outbound.model.ValidatedOrder;

@Mapper(componentModel = "spring")
public interface CommandMapper {
  ReceivedOrder getReceivedOrderMessage(Order order);

  ValidatedOrder getValidatedOrderMessage(ReceivedOrder source);
}
