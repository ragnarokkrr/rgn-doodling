package ragna.kafka.flightapi.plane.infra.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaneEvent {
  private String planeId;
  private String currentAirport;
}
