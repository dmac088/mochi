package io.nzbee.entity.product.department;

import io.nzbee.entity.IMapper;

public interface IDepartmentMapper extends IMapper<io.nzbee.domain.department.Department, DepartmentEntity> {

	io.nzbee.domain.department.Department entityToDo(DepartmentEntity department, String locale);

}
