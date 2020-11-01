package io.nzbee.entity.party.person;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;

public interface IPersonDao extends IDao<PersonEntity> {
	
	List<PersonEntity> findAllByRoleName(String roleClassType);

	Optional<PersonEntity> findByUsernameAndRole(String userName, String roleClassType);

}
