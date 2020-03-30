package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.entity.product.department.Department;

@Service(value = "productTypeEntityBeanFactory")
@Profile(value = "dev")
public class DepartmentEntityBeanFactory {

	@Bean
	public final Department getProductTypeEntityBean() {
		Department productType = new Department();
		productType.setCode("TST01");
		productType.setDepartmentClass("Food");
		return productType;
	}
	
}
