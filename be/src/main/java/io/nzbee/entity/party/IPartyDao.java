package io.nzbee.entity.party;

import java.util.Set;
import io.nzbee.entity.IDao;

public interface IPartyDao extends IDao<Party> {
	
	Set<Party> findAllByRoleName(String roleClassType);

}
