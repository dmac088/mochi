package io.nzbee.entity.party.person;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

	Optional<PersonEntity> findByPartyId(Long id);

	@Query(	  " SELECT new io.nzbee.entity.party.person.PersonDTO(a.partyId, a.firstName, a.lastName) "
			+ " FROM Person p "
			+ " JOIN User u "
			+ " ON p.partyId = u.partyId"
			+ "WHERE p.personId = :id ")
	Optional<CustomerDTO> findDTOByPartyId(Long id);
	
}
