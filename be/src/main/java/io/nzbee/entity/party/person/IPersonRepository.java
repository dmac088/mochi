package io.nzbee.entity.party.person;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<PersonEntity, Long> {

	Optional<PersonEntity> findByPartyId(Long id);

	@Query(	  " SELECT new io.nzbee.entity.party.person.PersonDTO(p.partyId, "
			+ "														p.givenName, "
			+ "														p.familyName, "
			+ "														u.username, "
			+ "														'1',"
			+ "														u.enabled) "
			+ " FROM PersonEntity p "
			+ " JOIN p.partyUser u "
			+ " WHERE p.partyId = :id ")
	Optional<PersonDTO> findDTOByPartyId(Long id);
	
}
