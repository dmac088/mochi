package io.nzbee.entity.product.department;

import org.springframework.stereotype.Component;
import io.nzbee.domain.department.Department;


@Component(value="departmentMapper")
public class DepartmentMapper implements IDepartmentMapper {

	
	
	@Override
	public Department DTOToDo(DepartmentDTO dto) {
		Department dO = 
				new Department(
						dto.getDepartmentCode(),
						dto.getDepartmentDesc(),
						dto.getLocale()
				);
	
		return dO;
	}


	@Override
	public DepartmentEntity doToEntity(Department d) {
		// TODO Auto-generated method stub
		return null;
	}

}
