package io.nzbee.domain.department;

import io.nzbee.domain.ILocalizedService;

public interface IDepartmentService extends ILocalizedService<Department> {

	Department findByProductCode(String locale, String currency, String departmentCode);


}
