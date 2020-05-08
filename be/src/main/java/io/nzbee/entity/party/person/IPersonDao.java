package io.nzbee.entity.party.person;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IPersonDao extends IDao<Person> {
	
	List<Person> findAllByRoleName(String roleClassType);

	Optional<Person> findByUsernameAndRole(String userName, String roleClassType);

}
