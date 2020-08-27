package io.nzbee.entity.product.department;

import io.nzbee.entity.IMapper;

public interface IDepartmentMapper extends IMapper<io.nzbee.domain.department.Department, Department> {

	io.nzbee.domain.department.Department entityToDo(Department department, String locale);

}
