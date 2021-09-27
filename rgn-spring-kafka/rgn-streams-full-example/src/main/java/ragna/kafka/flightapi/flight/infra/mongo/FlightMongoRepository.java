package ragna.kafka.flightapi.flight.infra.mongo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ragna.kafka.flightapi.flight.Flight;
import ragna.kafka.flightapi.flight.FlightRepository;
import ragna.kafka.flightapi.flight.FlightStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FlightMongoRepository implements FlightRepository {
  private final FlightMapper flightMapper;
  private final SpringFlightRepository springFlightRepository;

  @Override
  public String save(Flight flight) {
    final var saved = springFlightRepository.save(flightMapper.toDocument(flight));
    return saved.getId();
  }

  @Override
  public List<Flight> findConfirmedFligthsByPlaneId(String planeId) {
    final var flightDocuments =
        springFlightRepository.findByPlaneIdAndStatus(planeId, FlightStatus.CONFIRMED);

    return flightDocuments.stream().map(flightMapper::fromDocument).collect(Collectors.toList());
  }

  @Override
  public Optional<Flight> findById(String flightId) {
    return springFlightRepository.findById(flightId).map(flightMapper::fromDocument);
  }
}
