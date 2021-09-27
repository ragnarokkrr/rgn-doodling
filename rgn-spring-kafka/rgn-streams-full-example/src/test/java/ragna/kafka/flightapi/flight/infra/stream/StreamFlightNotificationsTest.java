package ragna.kafka.flightapi.flight.infra.stream;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.context.annotation.Import;
import ragna.kafka.flightapi.TestData;
import ragna.kafka.flightapi.messaging.CloudStreamTest;

import static org.assertj.core.api.Assertions.assertThat;

@CloudStreamTest
@Import(StreamFlightNotifications.class)
class StreamFlightNotificationsTest {
  @Autowired private OutputDestination target;
  @Autowired private StreamFlightNotifications notifications;

  @Test
  void flightArrived() throws JSONException {
    notifications.flightArrived(TestData.FLIGHT_ID);

    final var flightEvent = target.receive(0L, "flight-arrived-v1");

    final var jsonFlightEvent = new JSONObject(new String(flightEvent.getPayload()));

    assertThat(jsonFlightEvent.get("flightId")).isEqualTo(TestData.FLIGHT_ID);
  }
}
