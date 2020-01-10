package io.nzbee.entity.role;

import java.util.Optional;

public interface IRoleRepository extends RoleBaseRepository<Role>  {
	
	Optional<Role> findByRoleId(Long id);

}
