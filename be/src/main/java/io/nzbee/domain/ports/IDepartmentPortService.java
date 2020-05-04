package io.nzbee.domain.ports;

import io.nzbee.domain.department.Department;

public interface IDepartmentPortService extends IProductDimensionService<Department> {

	Department findByProductCode(String locale, String currency, String departmentCode);

}
