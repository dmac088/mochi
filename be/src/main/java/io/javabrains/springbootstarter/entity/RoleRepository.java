package io.javabrains.springbootstarter.entity;

import java.util.Optional;

public interface RoleRepository extends RoleBaseRepository<Role>  {
	
	Optional<Role> findByRoleId(Long id);

}
