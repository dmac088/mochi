package io.nzbee.entity.product.department;

import io.nzbee.entity.IMapper;

public interface IDepartmentMapper extends IMapper<Department, io.nzbee.entity.product.department.Department> {

	io.nzbee.domain.department.Department entityToDo(Department department, String locale);

}
