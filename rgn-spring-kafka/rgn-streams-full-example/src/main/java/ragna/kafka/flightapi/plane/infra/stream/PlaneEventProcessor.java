package ragna.kafka.flightapi.plane.infra.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ragna.kafka.flightapi.flight.FlightOperations;

import java.util.function.Function;

@Component("planeEventProcessor")
@RequiredArgsConstructor
public class PlaneEventProcessor implements Function<PlaneEvent, FlightEvent> {
  private final FlightOperations flightOperations;

  @Override
  public FlightEvent apply(PlaneEvent planeEvent) {
    final var planeId = planeEvent.getPlaneId();
    final var flight =
        flightOperations
            .findConfirmedFlightByPlaneId(planeId)
            .orElseThrow(
                () ->
                    new NoFlightFoundException(
                        String.format("No flight found for plane id %s", planeId)));
    return new FlightEvent(flight.getId(), planeEvent.getCurrentAirport());
  }
}
