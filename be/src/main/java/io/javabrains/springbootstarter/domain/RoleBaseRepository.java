package io.javabrains.springbootstarter.domain;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.CrudRepository;

@NoRepositoryBean
public interface RoleBaseRepository<R extends Role> extends CrudRepository<R, Long> { 
	public Optional<Role> findByRoleId(Long Id);

}


