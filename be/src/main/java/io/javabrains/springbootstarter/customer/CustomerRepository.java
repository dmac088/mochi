package io.javabrains.springbootstarter.customer;

import org.springframework.data.jpa.domain.Specification;
import io.javabrains.springbootstarter.role.RoleBaseRepository;

public interface CustomerRepository extends RoleBaseRepository<Customer> {

	//@Override
	Customer findOne(Long Id);
	
	//Customer findOneByCustomerId(String Id);
	
	Customer findOne(Specification<Customer> spec);
}
