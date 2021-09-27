package ragna.kafka.flightapi.flight.infra.stream;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import ragna.kafka.flightapi.flight.FlightNotifications;


@Component
@AllArgsConstructor
public class StreamFlightNotifications implements FlightNotifications {
    private final StreamBridge streamBridge;

    @Override
    public void flightArrived(String flightId) {
        streamBridge.send("flightArrived-out-0", new FlightArrivedEvent(flightId));
    }
}
