package ragna.springwebfluxcass.person.domain.repository;

import java.util.UUID;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import ragna.springwebfluxcass.person.domain.entity.PersonEntity;

@Repository
public interface PersonRepository extends ReactiveCassandraRepository<PersonEntity, UUID> {
}
