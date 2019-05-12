package io.nzbee.entity;

import java.util.Optional;

public interface RoleRepository extends RoleBaseRepository<Role>  {
	
	Optional<Role> findByRoleId(Long id);

}
