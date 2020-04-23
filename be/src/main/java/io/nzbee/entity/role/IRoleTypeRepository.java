package io.nzbee.entity.role;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IRoleTypeRepository extends CrudRepository<RoleType, Long>  {
	
	public Optional<RoleType> findByRoleTypeId(Long id);
	
	public Optional<RoleType> findByRoleTypeDesc(String desc);

}
