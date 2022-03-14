package ragna.query.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ragna.cqrs.query.model.Item;
import ragna.cqrs.query.model.OrderedItem;
import ragna.cqrs.query.model.ValidatedOrder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QueryMapper {

  @Mapping(source = "itemLabel", target = "label")
  OrderedItem getOrderedItem(ValidatedOrder validatedOrder);

  List<Item> getItems(List<OrderedItem> orderedItemList);
}
