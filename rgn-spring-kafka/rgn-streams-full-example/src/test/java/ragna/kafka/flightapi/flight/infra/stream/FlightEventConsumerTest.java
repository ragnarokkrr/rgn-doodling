package ragna.kafka.flightapi.flight.infra.stream;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;
import ragna.kafka.flightapi.TestData;
import ragna.kafka.flightapi.airport.Airport;
import ragna.kafka.flightapi.flight.FlightOperations;
import ragna.kafka.flightapi.messaging.CloudStreamTest;

import static org.mockito.Mockito.verify;

@CloudStreamTest
@Import(FlightEventConsumer.class)
class FlightEventConsumerTest {
  @Autowired private InputDestination source;

  @Autowired private OutputDestination target;

  @MockBean private FlightOperations flightOperations;

  @Test
  void shouldCallFlightOperationsWhenMessageIsReceived() throws JSONException {
    final var planeEvent =
        new JSONObject()
            .put("flightId", TestData.FLIGHT_ID)
            .put("currentAirport", TestData.CNH_CODE)
            .toString();
    source.send(new GenericMessage<>(planeEvent.getBytes()), "flight-events-v1");

    verify(flightOperations).flightArrivedIn(TestData.FLIGHT_ID, new Airport(TestData.CNH_CODE));
  }
}
