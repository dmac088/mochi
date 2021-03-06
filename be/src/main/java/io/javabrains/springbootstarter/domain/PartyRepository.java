package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<Party, Long> {

	List<Party> findAll();
	
	List<Party> findByPartyRolesRoleTypeRoleTypeDesc(String roleTypeDesc);
	
	Optional<Party> findByPartyId(Long id);
	
	Optional<Party> findByPartyUserUsername(String userName);

	
}
