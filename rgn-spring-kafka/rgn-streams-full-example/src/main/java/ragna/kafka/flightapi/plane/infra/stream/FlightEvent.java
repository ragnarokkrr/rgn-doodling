package ragna.kafka.flightapi.plane.infra.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FlightEvent {
  private final String flightId;
  private final String currentAirport;
}
