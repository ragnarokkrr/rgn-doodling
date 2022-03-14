package ragna.command.processor;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ragna.command.util.ProcessorUtil;
import ragna.cqrs.command.inbound.model.ReceivedOrder;
import ragna.cqrs.command.outbound.model.ValidatedOrder;

import java.util.function.Function;

@Component
public class CommandProcessor {

  @Bean
  public Function<KStream<String, ReceivedOrder>, KStream<String, ValidatedOrder>>
      orderProcessor() {
    return stringReceivedOrderKStream ->
        stringReceivedOrderKStream.mapValues(ProcessorUtil::validateOrder);
  }
}
