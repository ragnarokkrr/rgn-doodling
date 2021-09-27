package ragna.kafka.flightapi.flight.infra;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ragna.kafka.flightapi.TestData;
import ragna.kafka.flightapi.flight.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightFacadeTest {

  @Mock private FlightRepository flightRepository;
  @Mock private FlightNotifications flightNotifications;
  @InjectMocks FlightFacade flightFacade;

  @Captor private ArgumentCaptor<Flight> flightArgumentCaptor;

  private static final String CREATED_ID = "id";

  @Test
  void shouldSaveAsFlight() {
    when(flightRepository.save(TestData.FLIGHT)).thenReturn(CREATED_ID);

    final var id = flightFacade.create(TestData.FLIGHT);
    assertThat(id).isEqualTo(CREATED_ID);
    verify(flightRepository).save(TestData.FLIGHT);
  }

  @Test
  void shouldFindConfirmedFlightAndReturnEmptyWhenIsOneFlight() {
    final var id = "id1";
    when(flightRepository.findConfirmedFligthsByPlaneId(id)).thenReturn(new ArrayList<>());

    final var optionalFlight = flightFacade.findConfirmedFlightByPlaneId(id);

    assertThat(optionalFlight).isNotPresent();
  }

  @Test
  void shouldFindConfirmedFlightAndThrowIllegalStateWhenAreMoreThanOneFlight() {
    final var id = "id1";

    when(flightRepository.findConfirmedFligthsByPlaneId(id))
        .thenReturn(List.of(TestData.FLIGHT, TestData.FLIGHT));

    assertThrows(IllegalStateException.class, () -> flightFacade.findConfirmedFlightByPlaneId(id));
  }

  @Test
  void shouldNotSetStatusToArrivedWhenArrivesInAnotherAirport() {
    when(flightRepository.findById(TestData.FLIGHT_ID))
        .thenReturn(Optional.of(TestData.FLIGHT_WITH_ID));

    flightFacade.flightArrivedIn(TestData.FLIGHT_ID, TestData.POA);

    verify(flightRepository).findById(TestData.FLIGHT_ID);
    verify(flightRepository).save(flightArgumentCaptor.capture());

    final var savedFlight = flightArgumentCaptor.getValue();

    assertThat(savedFlight)
        .hasFieldOrPropertyWithValue("status", FlightStatus.CONFIRMED)
        .hasFieldOrPropertyWithValue("id", TestData.FLIGHT_ID);

    verifyNoInteractions(flightNotifications);
  }

  @Test
  void shouldThrowFlightNotFoundExceptionWenFlightDoesNotExist() {
    when(flightRepository.findById(TestData.FLIGHT_ID)).thenReturn(Optional.empty());

    final var exception =
        assertThrows(
            FlightNotFoundException.class,
            () -> flightFacade.flightArrivedIn(TestData.FLIGHT_ID, TestData.POA));

    verify(flightRepository).findById(TestData.FLIGHT_ID);
    verifyNoMoreInteractions(flightRepository);

    assertThat(exception.getMessage()).contains(TestData.FLIGHT_ID);
    verifyNoInteractions(flightNotifications);
  }
}
