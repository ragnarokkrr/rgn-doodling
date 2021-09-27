package ragna.kafka.flightapi.plane;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PlaneFacade implements PlaneOperations {
  private final PlaneRepository planeRepository;

  @Override
  public String create(Plane plane) {
    return planeRepository.save(plane);
  }
}
