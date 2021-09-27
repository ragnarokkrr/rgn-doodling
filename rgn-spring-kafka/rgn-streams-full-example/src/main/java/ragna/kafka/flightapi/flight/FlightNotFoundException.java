package ragna.kafka.flightapi.flight;

public class FlightNotFoundException extends RuntimeException {
  public FlightNotFoundException(String flightId) {
    super(String.format("flight %s not found", flightId));
  }
}
