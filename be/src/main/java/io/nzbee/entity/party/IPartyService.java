package io.nzbee.entity.party;

import java.util.List;

import io.nzbee.entity.IService;

public interface IPartyService extends IService<Party> {
	
	List<Party> findAll();

	List<Party> findByRoleTypeDesc(Class<?> roleType);
	
}
