package io.nzbee.entity.party.person;

import java.util.List;

import io.nzbee.entity.IDao;

public interface IPersonDao extends IDao<Person> {
	
	List<Person> findAllByRoleName(String roleClassType);

}
