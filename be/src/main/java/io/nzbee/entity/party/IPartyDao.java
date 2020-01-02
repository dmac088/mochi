package io.nzbee.entity.party;

import java.util.List;

import io.nzbee.entity.IDao;

public interface IPartyDao extends IDao<Party> {
	
	List<Party> findAllByRoleName(Class<?> roleClassType);

}
