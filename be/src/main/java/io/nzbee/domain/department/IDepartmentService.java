package io.nzbee.domain.department;

import java.util.Optional;

import io.nzbee.domain.ILocalizedService;

public interface IDepartmentService extends ILocalizedService<Department> {

	Optional<Department> findByProductCode(String locale, String currency, String departmentCode);


}
