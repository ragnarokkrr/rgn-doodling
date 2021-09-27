package ragna.kafka.flightapi.flight.infra.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FlightEvent {
  private String flightId;
  private String currentAirport;
}
