package ragna.kafka.flightapi.flight.infra.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ragna.kafka.flightapi.flight.FlightStatus;

@Document
@Data
class FlightDocument {
  private String id;
  private String planeId;
  private String originCode;
  private String destinationCode;
  private FlightStatus status;
}
