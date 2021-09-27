package ragna.kafka.flightapi.flight.infra.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ragna.kafka.flightapi.airport.Airport;
import ragna.kafka.flightapi.flight.FlightOperations;

import java.util.function.Consumer;

@Component("flightEventConsumer")
@RequiredArgsConstructor
public class FlightEventConsumer implements Consumer<FlightEvent> {
  private final FlightOperations flightOperations;

  @Override
  public void accept(FlightEvent flightEvent) {
    final var airport = new Airport(flightEvent.getCurrentAirport());
    flightOperations.flightArrivedIn(flightEvent.getFlightId(), airport);
  }
}
