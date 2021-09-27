package ragna.kafka.flightapi.plane.infra;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ragna.kafka.flightapi.TestData;
import ragna.kafka.flightapi.plane.PlaneFacade;
import ragna.kafka.flightapi.plane.PlaneRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaneFacadeTest {
  @Mock private PlaneRepository planeRepository;

  @InjectMocks private PlaneFacade planeFacade;

  private static final String CREATED_ID = "id";

  @Test
  void shouldCreateAPlane() {
    when(planeRepository.save(TestData.PLANE)).thenReturn(CREATED_ID);

    final var id = planeFacade.create(TestData.PLANE);

    assertThat(id).isEqualTo(CREATED_ID);
    verify(planeRepository).save(TestData.PLANE);
  }
}
