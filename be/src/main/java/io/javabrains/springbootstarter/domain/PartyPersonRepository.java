package io.javabrains.springbootstarter.domain;

import org.springframework.data.repository.CrudRepository;

public interface PartyPersonRepository extends CrudRepository<PartyPerson, Long> {

	PartyPerson findByPartyId(Long id);

}
