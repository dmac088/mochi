package io.javabrains.springbootstarter.role;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleTypeRepository extends CrudRepository<RoleType, Long> {

	//getAllTopics()
	//getTopic(id)
	//updateTopic(id)
	//deleteTopic(id) 

	public RoleType findByRoleTypeDesc(String roleTypeDesc);
	
	public List<Role> findByRoleTypeID(Long roleTypeId);
}
