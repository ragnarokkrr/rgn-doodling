package ragna.kafka.flightapi.flight;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ragna.kafka.flightapi.airport.Airport;

import java.util.Optional;

import static ragna.kafka.flightapi.flight.FlightStatus.ARRIVED;

@Service
@AllArgsConstructor
public class FlightFacade implements FlightOperations {
    private final FlightRepository flightRepository;
    private final FlightNotifications flightNotifications;

    @Override
    public String create(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> findConfirmedFlightByPlaneId(String planeId) {
        final var flights = flightRepository.findConfirmedFligthsByPlaneId(planeId);
        if (flights.isEmpty()) {
            return Optional.empty();
        }
        if (flights.size() > 1) {
            throw new IllegalStateException("There should only be one confirmed flight for a plane");
        }
        return Optional.of(flights.get(0));
    }

    @Override
    public void flightArrivedIn(String flightId, Airport airport) {
        final var flight = flightRepository
                .findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException(flightId));
        final var updatedFlight = flight.arrivedIn(airport);
        flightRepository.save(updatedFlight);

        if (updatedFlight.getStatus() == ARRIVED) {
            flightNotifications.flightArrived(updatedFlight.getId());
        }
    }
}
