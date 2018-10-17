package io.javabrains.springbootstarter.domain;


public interface RoleRepository extends RoleBaseRepository<Role>  {
	
	Role findOne(Long id);

}
