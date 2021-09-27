package ragna.kafka.flightapi.plane.infra.stream;

public class NoFlightFoundException extends RuntimeException {
  public NoFlightFoundException(String msg) {
    super(msg);
  }
}
