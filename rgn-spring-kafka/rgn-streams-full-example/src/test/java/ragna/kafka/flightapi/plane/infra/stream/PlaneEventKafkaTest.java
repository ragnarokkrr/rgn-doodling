package ragna.kafka.flightapi.plane.infra.stream;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import ragna.kafka.flightapi.TestData;
import ragna.kafka.flightapi.flight.FlightOperations;
import ragna.kafka.flightapi.messaging.utils.KafkaTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@EmbeddedKafka(
    topics = {"plane-events-v1", "flight-events-v1", "plane-events-dlq-v1", "flight-events-dlq-v1"},
    bootstrapServersProperty = "spring.cloud.stream.kafka.binder.brokers")
class PlaneEventKafkaTest {

  @Autowired private EmbeddedKafkaBroker broker;
  @MockBean FlightOperations flightOperations;

  private KafkaTestUtils kafkaTestUtils;

  @BeforeEach
  void prepare() {
    this.kafkaTestUtils = new KafkaTestUtils(broker);
  }

  @Test
  void shouldTransformPlanetEventToFlightEvent() throws JSONException {
    when(flightOperations.findConfirmedFlightByPlaneId(TestData.PLANE_ID))
        .thenReturn(Optional.of(TestData.FLIGHT_WITH_ID));

    final var consumer = kafkaTestUtils.createConsumer("flight-events-v1");

    final var planeEvent =
        new JSONObject()
            .put("planeId", TestData.PLANE_ID)
            .put("currentAirport", TestData.CNH_CODE)
            .toString();

    kafkaTestUtils.sendMessage("plane-events-v1", planeEvent);

    final var record = kafkaTestUtils.getNextRecord(consumer, "flight-events-v1");

    final var jsonFlightEvent = new JSONObject(record.value());

    assertThat(jsonFlightEvent.get("currentAirport")).isEqualTo(TestData.CNH_CODE);
    assertThat(jsonFlightEvent.get("flightId")).isEqualTo(TestData.FLIGHT_ID);
  }

  @Test
  void shouldSendToDeadLetterWhenThereIsNoFlight() throws JSONException {
    when(flightOperations.findConfirmedFlightByPlaneId(TestData.PLANE_ID))
        .thenReturn(Optional.empty());

    final var consumer = kafkaTestUtils.createConsumer("plane-events-dlq-v1");

    final var planeEvent =
        new JSONObject()
            .put("planeId", TestData.PLANE_ID)
            .put("currentAirport", TestData.CNH_CODE)
            .toString();

    kafkaTestUtils.sendMessage("plane-events-v1", planeEvent);

    final var record = kafkaTestUtils.getNextRecord(consumer, "plane-events-dlq-v1");

    final var jsonFlightEvent = new JSONObject(record.value());

    assertThat(jsonFlightEvent.get("currentAirport")).isEqualTo(TestData.CNH_CODE);
    assertThat(jsonFlightEvent.get("planeId")).isEqualTo(TestData.PLANE_ID);
    final var exceptionMessage =
        new String(record.headers().lastHeader("x-exception-message").value());
    assertThat(exceptionMessage).contains("NoFlightFoundException");
  }
}
