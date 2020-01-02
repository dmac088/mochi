package io.nzbee.entity.party;

import java.util.List;

import io.nzbee.entity.IDao;

public interface IPartyDao extends IDao<Party> {

	List<Party> findAll(String locale, String currency);
	
	List<Party> findByRoleName(String RoleName);
	
	List<Party> findByRoleCode(String RoleCode);

}
