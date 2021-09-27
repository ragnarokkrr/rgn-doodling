package ragna.kafka.flightapi.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ragna.kafka.flightapi.airport.Airport;
import ragna.kafka.flightapi.plane.Plane;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Flight {
  private final String id;
  private final Plane plane;
  private final Airport origin;
  private final Airport destination;
  private final FlightStatus status;

  public Flight arrivedIn(Airport airport) {
    if (airport.equals(destination)) {
      return this.toBuilder().status(FlightStatus.ARRIVED).build();
    }
    return this;
  }
}
