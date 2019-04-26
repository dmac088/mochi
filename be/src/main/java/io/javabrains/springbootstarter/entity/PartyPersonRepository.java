package io.javabrains.springbootstarter.entity;

import org.springframework.data.repository.CrudRepository;

public interface PartyPersonRepository extends CrudRepository<PartyPerson, Long> {

	PartyPerson findByPartyId(Long id);

}
