package io.nzbee.test.unit.domain.beans;

import io.nzbee.domain.department.Department;

public class DepartmentDoBeanFactory {

	public final Department getDepartmentDoBean() {
		
		return new Department(	"TST01",
								"Test Department 1",
								"en-GB");
		
	}
	
}
