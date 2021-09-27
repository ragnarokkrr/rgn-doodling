package ragna.kafka.flightapi.flight.infra.mongo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ragna.kafka.flightapi.flight.Flight;

@Mapper(componentModel = "spring")
public interface FlightMapper {

  @Mapping(source = "plane.id", target = "planeId")
  @Mapping(source = "origin.code", target = "originCode")
  @Mapping(source = "destination.code", target = "destinationCode")
  FlightDocument toDocument(Flight flight);

  @Mapping(source = "planeId", target = "plane.id")
  @Mapping(source = "originCode", target = "origin.code")
  @Mapping(source = "destinationCode", target = "destination.code")
  Flight fromDocument(FlightDocument flightDocument);
}
