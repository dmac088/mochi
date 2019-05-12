package io.nzbee.entity;

import org.springframework.data.repository.CrudRepository;

public interface PartyPersonRepository extends CrudRepository<PartyPerson, Long> {

	PartyPerson findByPartyId(Long id);

}
