package io.nzbee.test.integration.domain.beans.department;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.department.Department;

@Service
@Profile(value = "it")
public class DepartmentDoBeanFactory implements IDepartmentDoBeanFactory {

	@Override
	public Department getBean() {
		
		return new Department(	"ACC01",
								"Accessories",
								"en-GB");
		
	}
	
}
