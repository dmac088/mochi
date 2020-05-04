package io.nzbee.entity.product.department;

import org.springframework.stereotype.Component;

@Component(value="departmentMapper")
public class DepartmentMapper implements IDepartmentMapper {

	@Override
	public io.nzbee.domain.department.Department entityToDo(Department e, String locale, String currency) {
		io.nzbee.domain.department.Department dO = 
				new io.nzbee.domain.department.Department(
						e.getDepartmentCode(),
						e.getAttribute().getDesc(),
						locale, 
						currency
				);
	
		return dO;
	}

}
