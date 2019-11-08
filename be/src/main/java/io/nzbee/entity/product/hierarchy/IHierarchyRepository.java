package io.nzbee.entity.product.hierarchy;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface HierarchyRepository extends CrudRepository<Hierarchy, Long> {
	
	List<Hierarchy> findAll();
	
	Hierarchy findByHierarchyId(Long id);
	
	Hierarchy findByHierarchyCode(String code);
}

