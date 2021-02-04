package ragna.saga.choreography.events.inventory;

import lombok.Data;
import ragna.saga.choreography.dto.InventoryDto;
import ragna.saga.choreography.events.Event;

import java.util.Date;
import java.util.UUID;

@Data
public class InventoryEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private InventoryDto inventory;
    private InvetoryStatus status;

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }
}
