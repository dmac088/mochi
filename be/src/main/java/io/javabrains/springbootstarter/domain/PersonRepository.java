package io.javabrains.springbootstarter.domain;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Person findByPartyId(Long id);

}
