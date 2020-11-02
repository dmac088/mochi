package io.nzbee.entity.product.department;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IDepartmentRepository extends CrudRepository<DepartmentEntity, Long> {
	
	@Override
    Set<DepartmentEntity> findAll();
	
	Optional<DepartmentEntity> findByDepartmentCode(String departmentCode);
}

