package io.nzbee.entity.party;

import java.util.List;

import io.nzbee.entity.IDao;
import io.nzbee.entity.role.Role;

public interface IPartyDao extends IDao<Party> {
	
	List<Party> findAllByRoleName(Class<Role> roleClassType);

}
