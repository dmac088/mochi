package io.nzbee.entity.party.person;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPersonService extends IService<Person> {

	Optional<Person> findByUsernameAndRole(String userName, Class<?> roleType);
	
}
