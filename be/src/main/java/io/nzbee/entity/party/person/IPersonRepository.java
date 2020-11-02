package io.nzbee.entity.party.person;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<PersonEntity, Long> {

	Optional<PersonEntity> findByPartyId(Long id);

	@Query(	  " SELECT new io.nzbee.entity.party.person.CustomerDTO(a.partyId, "
			+ "														a.firstName, "
			+ "														a.lastName, "
			+ "														u.userName, "
			+ "														c.customerNumber,"
			+ "														u.isEnabled) "
			+ " FROM PersonEntity p "
			+ " JOIN UserEntity u "
			+ " JOIN CustomerEntity c "
			+ " WHERE p.personId = :id ")
	Optional<CustomerDTO> findDTOByPartyId(Long id);
	
}
