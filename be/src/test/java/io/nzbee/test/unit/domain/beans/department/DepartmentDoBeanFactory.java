package io.nzbee.test.unit.domain.beans.department;

import io.nzbee.domain.department.Department;

public class DepartmentDoBeanFactory implements IDepartmentDoBeanFactory {

	public final Department getBean() {
		
		return new Department(	"TST01",
								"Test Department 1",
								"en-GB");
		
	}
	
}
