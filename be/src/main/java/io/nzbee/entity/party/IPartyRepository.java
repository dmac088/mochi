package io.nzbee.entity.party;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IPartyRepository extends CrudRepository<Party, Long> {

	Set<Party> findAll();
	
	Set<Party> findByPartyRolesRoleTypeRoleTypeDesc(String roleTypeDesc);
	
	Optional<Party> findByPartyUserUsername(String userName);

}
