package ragna.query.controller;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ragna.cqrs.query.model.Item;
import ragna.cqrs.query.model.OrderedItemsList;
import ragna.cqrs.query.model.Price;
import ragna.query.mapper.QueryMapper;
import ragna.query.processor.QueryProcessor;

import java.util.List;

@RestController
public class QueryController {
  @Autowired private InteractiveQueryService queryService;

  @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Item>> getItemsByCustomerIdAndPrice(
      @RequestParam(value = "customerId") String customerId, @RequestParam("price") Price price) {
    // get item store for the given colours
    final var storeName = price.label + QueryProcessor.ITEM_STORE_SUFFIX;

    final ReadOnlyKeyValueStore<String, OrderedItemsList> orderedItemStore =
        queryService.getQueryableStore(storeName, QueryableStoreTypes.keyValueStore());

    // get the items for teh given customer
    final var orderedItemsList = orderedItemStore.get(customerId);
    if (orderedItemsList != null) {
      final var itemList =
          Mappers.getMapper(QueryMapper.class).getItems(orderedItemsList.getItems());
      return ResponseEntity.ok(itemList);
    }

    return ResponseEntity.notFound().build();
  }
}
