package io.nzbee.entity.product.department;

import java.util.Optional;

import io.nzbee.entity.ILocalizedService;

public interface IDepartmentService extends ILocalizedService<Department> {

	Optional<Department> findByProductCode(String locale, String productCode);
	
}
