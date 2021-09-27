package ragna.kafka.flightapi.plane.infra.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import ragna.kafka.flightapi.plane.Plane;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Import({MongoPlaneRepository.class, PlaneMapperImpl.class})
class MongoPlaneRepositoryTest {
  @Autowired private MongoPlaneRepository mongoPlaneRepository;
  @Autowired MongoTemplate mongoTemplate;

  private static final String CODE = "PR-MYK";
  private static final String TYPE = "Airbus A320";

  @Test
  void shouldCreateAPlane() {
    final var plane = Plane.builder().code(CODE).type(TYPE).build();

    final var id = mongoPlaneRepository.save(plane);

    assertThat(id).isNotEmpty();
    assertThat(mongoTemplate.findAll(PlaneDocument.class)).hasSize(1);
  }
}
