package io.nzbee.entity.person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Person findByPartyId(Long id);

}
