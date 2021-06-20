package io.nzbee.entity.product.department;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.ILightLocalizedService;

public interface IDepartmentService extends ILightLocalizedService<DepartmentDTO, DepartmentEntity> {

	Optional<DepartmentEntity> findByCode(String code);

	Optional<DepartmentEntity> findById(Long id);
	
	List<DepartmentEntity> findAll();

}
