package io.nzbee.entity.party.person;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPersonService extends IService<PersonEntity> {

	boolean userExists(String userName, Class<?> roleType);

	Optional<PersonEntity> findByUsernameAndRole(String userName, String roleType);
	
}
