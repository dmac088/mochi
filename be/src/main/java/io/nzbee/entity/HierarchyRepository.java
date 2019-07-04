package io.nzbee.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface HierarchyRepository extends CrudRepository<Hierarchy, Long> {
	
	List<Hierarchy> findAll();
	
	Hierarchy findByHierarchyId(Long id);
	
	Hierarchy findByCode(String code);
}

