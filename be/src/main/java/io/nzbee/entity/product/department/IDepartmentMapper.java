package io.nzbee.entity.product.department;

import io.nzbee.entity.IMapper;

public interface IDepartmentMapper extends IMapper<Department, io.nzbee.entity.product.department.Department> {

	io.nzbee.domain.department.Department entityToDo(io.nzbee.entity.product.department.Department e, String locale, String currency);

	io.nzbee.domain.department.Department entityToDo(Department e);

}
