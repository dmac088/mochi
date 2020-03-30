package io.nzbee.entity.product.department;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IDepartmentRepository extends CrudRepository<Department, Long> {

	Optional<Department> findByDepartmentCode(String code);
	
}
