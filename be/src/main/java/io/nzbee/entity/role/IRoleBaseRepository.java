package io.nzbee.entity.role;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.CrudRepository;

@NoRepositoryBean
public interface IRoleBaseRepository<R extends Role> extends CrudRepository<R, Long> { 
	public Optional<R> findByRoleId(Long Id);

}


