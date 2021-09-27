package ragna.kafka.flightapi.plane.infra.mongo;

import org.mapstruct.Mapper;
import ragna.kafka.flightapi.plane.Plane;

@Mapper(componentModel = "spring")
interface PlaneMapper {

  PlaneDocument toDocument(Plane plane);
}
