package ragna.command.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ragna.cqrs.command.inbound.model.ReceivedOrder;
import ragna.cqrs.command.inbound.model.ReceivedOrder.Builder;
import ragna.cqrs.command.model.Order;
import ragna.cqrs.command.outbound.model.ValidatedOrder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-28T09:30:09+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.1 (Azul Systems, Inc.)"
)
@Component
public class CommandMapperImpl implements CommandMapper {

    @Override
    public ReceivedOrder getReceivedOrderMessage(Order order) {
        if ( order == null ) {
            return null;
        }

        Builder receivedOrder = ReceivedOrder.newBuilder();

        receivedOrder.setOrderId( order.getOrderId() );
        receivedOrder.setCustomerId( order.getCustomerId() );
        receivedOrder.setItemLabel( order.getItemLabel() );
        receivedOrder.setItemPrice( order.getItemPrice() );

        return receivedOrder.build();
    }

    @Override
    public ValidatedOrder getValidatedOrderMessage(ReceivedOrder source) {
        if ( source == null ) {
            return null;
        }

        ragna.cqrs.command.outbound.model.ValidatedOrder.Builder validatedOrder = ValidatedOrder.newBuilder();

        validatedOrder.setOrderId( source.getOrderId() );
        validatedOrder.setCustomerId( source.getCustomerId() );
        validatedOrder.setItemLabel( source.getItemLabel() );
        validatedOrder.setItemPrice( source.getItemPrice() );
        validatedOrder.setTsReceived( source.getTsReceived() );

        return validatedOrder.build();
    }
}
