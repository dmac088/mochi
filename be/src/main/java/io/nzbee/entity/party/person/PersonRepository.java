package io.nzbee.entity.party.person;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Optional<Person> findByPartyId(Long id);

}
