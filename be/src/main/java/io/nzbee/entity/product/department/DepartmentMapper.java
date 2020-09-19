package io.nzbee.entity.product.department;

import org.springframework.stereotype.Component;

@Component(value="departmentMapper")
public class DepartmentMapper implements IDepartmentMapper {

	@Override
	public io.nzbee.domain.department.Department entityToDo(Department e, String locale) {
		io.nzbee.domain.department.Department dO = 
				new io.nzbee.domain.department.Department(
						e.getDepartmentCode(),
						e.getAttribute().getDesc(),
						locale
				);
	
		return dO;
	}

	@Override
	public io.nzbee.domain.department.Department entityToDo(Department e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department doToEntity(io.nzbee.domain.department.Department d) {
		// TODO Auto-generated method stub
		return null;
	}

}