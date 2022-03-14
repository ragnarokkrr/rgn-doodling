package ragna.query.util;

import org.apache.kafka.streams.KeyValue;
import org.mapstruct.factory.Mappers;
import ragna.cqrs.query.model.OrderedItem;
import ragna.cqrs.query.model.OrderedItemsList;
import ragna.cqrs.query.model.ValidatedOrder;
import ragna.query.mapper.QueryMapper;

import java.util.ArrayList;

public class ProcessorUtil {
  private ProcessorUtil() {}

  public static KeyValue<String, OrderedItem> newOrderedItemKV(
      String customerId, ValidatedOrder validatedOrder) {
    return new KeyValue<>(
        customerId, Mappers.getMapper(QueryMapper.class).getOrderedItem(validatedOrder));
  }

  public static OrderedItemsList initializeItems() {
    return OrderedItemsList.newBuilder().setItems(new ArrayList<>()).build();
  }

  public static OrderedItemsList aggregateItems(
      String aggregateKey, OrderedItem newValue, OrderedItemsList aggregateValue) {
    final var index = aggregateValue.getItems().indexOf(newValue);


    if (index >= 0) {
      final var orderedItem = aggregateValue.getItems().get(index);
      final var quantity = orderedItem.getQuantity();
      orderedItem.setQuantity(quantity + 1);
      return aggregateValue;
    }

    aggregateValue.getItems().add(newValue);
    return aggregateValue;
  }
}
