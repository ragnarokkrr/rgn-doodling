package ragna.query.processor;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ragna.cqrs.query.model.OrderedItem;
import ragna.cqrs.query.model.Price;
import ragna.cqrs.query.model.ValidatedOrder;
import ragna.query.util.ProcessorUtil;

import java.util.function.Function;

@Component
public class QueryProcessor {
  public static final String ITEM_STORE_SUFFIX = "-items-store";

  Predicate<String, OrderedItem> isItemCheap = (k, v) -> v.getPrice() < 50;
  Predicate<String, OrderedItem> isItemAffordable =
      (k, v) -> v.getPrice() >= 5 && v.getPrice() < 50;
  Predicate<String, OrderedItem> isItemExpensive = (k, v) -> v.getPrice() > 50;

  @Bean
  public Function<KStream<String, ValidatedOrder>, KStream<String, OrderedItem>[]> itemProcessor() {
    return validatedOrderKStream -> {
      // group the ordered item by price
      @SuppressWarnings("unchecked")
      final var orderedItemsByPriceStream =
          validatedOrderKStream
              .map(ProcessorUtil::newOrderedItemKV)
              .branch(isItemCheap, isItemAffordable, isItemExpensive);

      // materialize the groups items into separate state stores
      // cheap items
      orderedItemsByPriceStream[0]
          .groupByKey()
          .aggregate(
              ProcessorUtil::initializeItems,
              ProcessorUtil::aggregateItems,
              Materialized.as(Price.CHEAP.label + ITEM_STORE_SUFFIX));

      // affordable items
      orderedItemsByPriceStream[1]
          .groupByKey()
          .aggregate(
              ProcessorUtil::initializeItems,
              ProcessorUtil::aggregateItems,
              Materialized.as(Price.AFFORDABLE.label + ITEM_STORE_SUFFIX));

      // expensive items
      orderedItemsByPriceStream[2]
          .groupByKey()
          .aggregate(
              ProcessorUtil::initializeItems,
              ProcessorUtil::aggregateItems,
              Materialized.as(Price.EXPENSIVE.label + ITEM_STORE_SUFFIX));

      return orderedItemsByPriceStream;
    };
  }
}
