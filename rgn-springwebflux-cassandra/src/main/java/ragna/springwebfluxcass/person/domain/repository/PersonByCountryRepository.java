package ragna.springwebfluxcass.person.domain.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import ragna.springwebfluxcass.person.domain.entity.PersonByCountryEntity;
import ragna.springwebfluxcass.person.domain.entity.PersonByCountryKey;
import reactor.core.publisher.Flux;

@Repository
public interface PersonByCountryRepository extends ReactiveCassandraRepository<PersonByCountryEntity, PersonByCountryKey> {

    Flux<PersonByCountryEntity> findAllByKeyCountry(final String country);
}
