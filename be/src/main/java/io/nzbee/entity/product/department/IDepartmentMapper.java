package io.nzbee.entity.product.department;

import java.util.Optional;

import io.nzbee.entity.IMapper;

public interface IDepartmentMapper extends IMapper<Department, io.nzbee.entity.product.department.Department> {

	Optional<io.nzbee.domain.department.Department> entityToDo(Optional<Department> e, String locale, String currency);

}
