package ragna.kafka.flightapi.flight.infra.stream;

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
import ragna.kafka.flightapi.airport.Airport;
import ragna.kafka.flightapi.flight.FlightOperations;
import ragna.kafka.flightapi.messaging.utils.KafkaTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
@EmbeddedKafka(
    topics = {"plane-events-v1", "flight-events-v1", "plane-events-dlq-v1", "flight-events-dlq-v1"},
    bootstrapServersProperty = "spring.cloud.stream.kafka.binder.brokers")
class FlightEventKafkaTest {
  public static final int DLQ_NUMBER_OF_RETRIES = 3;
  @Autowired private EmbeddedKafkaBroker broker;
  @MockBean private FlightOperations flightOperations;
  private KafkaTestUtils kafkaTestUtils;

  @BeforeEach
  void prepare() {
    this.kafkaTestUtils = new KafkaTestUtils(broker);
  }

  @Test
  void shouldCallFlightOperationsWhenMessageIsReceived() throws JSONException {
    final var flightEvent =
        new JSONObject()
            .put("flightId", TestData.FLIGHT_ID)
            .put("currentAirport", TestData.CNH_CODE)
            .toString();
    kafkaTestUtils.sendMessage("flight-events-v1", flightEvent);
    await()
        .untilAsserted(
            () ->
                verify(flightOperations)
                    .flightArrivedIn(TestData.FLIGHT_ID, new Airport(TestData.CNH_CODE)));
  }

  @Test
  void shouldSendToDeadLetterWhenAnExceptionHappens() throws JSONException {
    doThrow(RuntimeException.class)
        .when(flightOperations)
        .flightArrivedIn(TestData.FLIGHT_ID, new Airport(TestData.CNH_CODE));

    final var consumer = kafkaTestUtils.createConsumer("flight-events-dlq-v1");

    final var flightEvent =
        new JSONObject()
            .put("flightId", TestData.FLIGHT_ID)
            .put("currentAirport", TestData.CNH_CODE)
            .toString();

    kafkaTestUtils.sendMessage("flight-events-v1", flightEvent);

    final var record = kafkaTestUtils.getNextRecord(consumer, "flight-events-dlq-v1");

    final var jsonFlightObject = new JSONObject(record.value());

    assertThat(jsonFlightObject.get("currentAirport")).isEqualTo(TestData.CNH_CODE);
    assertThat(jsonFlightObject.get("flightId")).isEqualTo(TestData.FLIGHT_ID);
    final var exceptionMessage =
        new String(record.headers().lastHeader("x-exception-message").value());
    assertThat(exceptionMessage).contains("RuntimeException");

    verify(flightOperations, times(DLQ_NUMBER_OF_RETRIES))
        .flightArrivedIn(TestData.FLIGHT_ID, new Airport(TestData.CNH_CODE));
  }
}
