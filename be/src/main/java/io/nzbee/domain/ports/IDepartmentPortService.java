package io.nzbee.domain.ports;

import java.util.Optional;

import io.nzbee.domain.department.Department;

public interface IDepartmentPortService extends IProductDimensionService<Department> {

	Optional<Department> findByProductCode(String locale, String currency, String departmentCode);

}
