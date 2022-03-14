package ragna.command.util;

import org.mapstruct.factory.Mappers;
import ragna.command.mapper.CommandMapper;
import ragna.cqrs.command.inbound.model.ReceivedOrder;
import ragna.cqrs.command.outbound.model.ValidatedOrder;

import java.time.Instant;

public class ProcessorUtil {
  private ProcessorUtil() {}

  public static ValidatedOrder validateOrder(ReceivedOrder receivedOrder) {
    final var validatedOrderMessage =
        Mappers.getMapper(CommandMapper.class).getValidatedOrderMessage(receivedOrder);
    // do the required things
    validatedOrderMessage.setTsValidated(Instant.now().toEpochMilli());
    return validatedOrderMessage;
  }
}
