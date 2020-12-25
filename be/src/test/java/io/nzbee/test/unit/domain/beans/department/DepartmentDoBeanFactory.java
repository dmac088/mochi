package io.nzbee.test.unit.domain.beans.department;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.department.Department;

@Service
@Profile(value = "ut")
public class DepartmentDoBeanFactory implements IDepartmentDoBeanFactory {

	@Override
	public final Department getBean() {
		
		return new Department(	"TST01",
								"Test Department 1",
								"en-GB");
		
	}
	
}
