package io.nzbee.entity.product.department;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IDepartmentRepository extends CrudRepository<DepartmentEntity, Long> {
	
	@Override
	List<DepartmentEntity> findAll();
	
	Optional<DepartmentEntity> findByDepartmentCode(String departmentCode);
}

