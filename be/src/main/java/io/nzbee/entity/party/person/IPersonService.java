package io.nzbee.entity.party.person;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPersonService extends IService<PersonEntity> {

	Optional<PersonEntity> findByUsernameAndRole(String userName, String roleType);

	boolean userExists(String userName, String roleType);

	List<PersonEntity> findAll();

	Optional<PersonEntity> findById(Long id);
	
}
