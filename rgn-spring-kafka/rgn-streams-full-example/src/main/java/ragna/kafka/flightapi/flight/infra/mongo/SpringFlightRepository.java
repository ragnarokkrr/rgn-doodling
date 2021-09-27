package ragna.kafka.flightapi.flight.infra.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ragna.kafka.flightapi.flight.FlightStatus;

import java.util.List;

@Repository
public interface SpringFlightRepository extends MongoRepository<FlightDocument, String> {
    List<FlightDocument> findByPlaneIdAndStatus(String planeId, FlightStatus status);
}
