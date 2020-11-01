package io.nzbee.entity.party.person;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPersonService extends IService<PersonEntity> {

	Optional<PersonEntity> findByUsernameAndRole(String userName, Class<?> roleType);

	boolean userExists(String userName, Class<?> roleType);
	
}
