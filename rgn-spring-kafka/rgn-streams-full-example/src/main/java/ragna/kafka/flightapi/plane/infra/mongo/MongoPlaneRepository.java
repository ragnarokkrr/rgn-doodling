package ragna.kafka.flightapi.plane.infra.mongo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ragna.kafka.flightapi.plane.Plane;
import ragna.kafka.flightapi.plane.PlaneRepository;

@Component
@AllArgsConstructor
class MongoPlaneRepository implements PlaneRepository {
  private final PlaneMapper planeMapper;
  private final SpringPlaneRepository springPlaneRepository;

  @Override
  public String save(Plane plane) {
    final var savedPlaneDocument = springPlaneRepository.save(planeMapper.toDocument(plane));
    return savedPlaneDocument.getId();
  }
}
