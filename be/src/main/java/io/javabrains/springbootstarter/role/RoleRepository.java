package io.javabrains.springbootstarter.role;


public interface RoleRepository extends RoleBaseRepository<Role>  {
	
	Role findOne(Long id);

}
