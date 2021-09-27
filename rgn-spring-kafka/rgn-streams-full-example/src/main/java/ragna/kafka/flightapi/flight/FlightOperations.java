package ragna.kafka.flightapi.flight;

import ragna.kafka.flightapi.airport.Airport;

import java.util.Optional;

public interface FlightOperations {
  String create(Flight flight);

  Optional<Flight> findConfirmedFlightByPlaneId(String planeId);

  void flightArrivedIn(String flightId, Airport airport);
}
