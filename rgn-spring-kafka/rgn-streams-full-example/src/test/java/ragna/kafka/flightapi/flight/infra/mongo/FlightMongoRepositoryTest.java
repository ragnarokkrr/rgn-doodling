package ragna.kafka.flightapi.flight.infra.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import ragna.kafka.flightapi.TestData;
import ragna.kafka.flightapi.flight.Flight;
import ragna.kafka.flightapi.flight.FlightStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@Import({FlightMongoRepository.class, FlightMapperImpl.class})
class FlightMongoRepositoryTest {
  @Autowired private FlightMongoRepository flightMongoRepository;

  @Autowired private MongoTemplate mongoTemplate;

  @Test
  void shouldSaveFlight() {
    final var flight =
        Flight.builder()
            .plane(TestData.PLANE_WITH_ID)
            .origin(TestData.POA)
            .destination(TestData.CNH)
            .status(FlightStatus.CONFIRMED)
            .build();

    final var id = flightMongoRepository.save(flight);

    assertThat(id).isNotEmpty();

    final var flightDocument = mongoTemplate.findById(id, FlightDocument.class);

    assertThat(flightDocument)
        .hasFieldOrPropertyWithValue("planeId", TestData.PLANE_ID)
        .hasFieldOrPropertyWithValue("originCode", TestData.POA_CODE)
        .hasFieldOrPropertyWithValue("destinationCode", TestData.CNH_CODE)
        .hasFieldOrPropertyWithValue("status", FlightStatus.CONFIRMED);
  }

  @Test
  void shouldUpdateAFlightStatusWhenCallingSave() {
    final var flight =
        Flight.builder()
            .plane(TestData.PLANE_WITH_ID)
            .origin(TestData.POA)
            .destination(TestData.CNH)
            .status(FlightStatus.CONFIRMED)
            .build();

    final var id = flightMongoRepository.save(flight);
    final var updatedFlight = flight.toBuilder().id(id).status(FlightStatus.ARRIVED).build();

    flightMongoRepository.save(updatedFlight);

    final var flightDb = flightMongoRepository.findById(id).get();

    assertThat(flightDb).hasFieldOrPropertyWithValue("status", FlightStatus.ARRIVED);
  }

  @Test
  void shouldFindAFlightById() {
    flightMongoRepository.save(TestData.FLIGHT_WITH_ID);
    flightMongoRepository.save(TestData.FLIGHT_WITH_ID_2);

    final var flightOptional = flightMongoRepository.findById(TestData.FLIGHT_ID);
    assertThat(flightOptional).isPresent();

    final var flight = flightOptional.get();
    assertThat(flight)
        .hasFieldOrPropertyWithValue("id", TestData.FLIGHT_ID)
        .hasFieldOrPropertyWithValue("plane.id", TestData.PLANE_ID);
  }

  @Test
  void shouldNotFindAFlightBIdWhenItDoesNotExist() {
    flightMongoRepository.save(TestData.FLIGHT_WITH_ID);
    flightMongoRepository.save(TestData.FLIGHT_WITH_ID_2);

    final var flightOptional = flightMongoRepository.findById("wrongId");

    assertThat(flightOptional).isEmpty();
  }
}
