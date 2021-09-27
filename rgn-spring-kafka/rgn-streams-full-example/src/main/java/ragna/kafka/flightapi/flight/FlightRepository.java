package ragna.kafka.flightapi.flight;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {
    String save(Flight flight);

    List<Flight> findConfirmedFligthsByPlaneId(String planeId);

    Optional<Flight> findById(String flightId);
}
