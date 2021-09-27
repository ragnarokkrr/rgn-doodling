package ragna.kafka.flightapi.plane.infra.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class PlaneDocument {
  @Id private String id;

  private String code;
  private String type;
}
