package io.javabrains.springbootstarter.role;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.CrudRepository;

@NoRepositoryBean
public interface RoleBaseRepository<R extends Role> extends CrudRepository<R, Long> { 
	public R findByroleId(Long Id);
}


