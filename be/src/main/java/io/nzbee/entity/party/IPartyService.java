package io.nzbee.entity.party;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.IService;

public interface IPartyService extends IService<Party> {

	Set<Party> findByRoleType(Class<?> roleType);
	
	Optional<Party> findByUsername(String userName);
}
