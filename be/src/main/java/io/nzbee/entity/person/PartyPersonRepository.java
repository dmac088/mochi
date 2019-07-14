package io.nzbee.entity.person;

import org.springframework.data.repository.CrudRepository;

public interface PartyPersonRepository extends CrudRepository<PartyPerson, Long> {

	PartyPerson findByPartyId(Long id);

}
