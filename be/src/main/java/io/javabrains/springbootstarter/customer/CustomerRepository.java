package io.javabrains.springbootstarter.customer;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import io.javabrains.springbootstarter.role.RoleBaseRepository;

public interface CustomerRepository extends RoleBaseRepository<Customer> {

	
	List<Customer> findAll();
	
	//@Override
	Customer findOne(Long Id);
	
	//Customer findOneByCustomerId(String Id);
	//need to use specification since findByCustomerId causes an exception
	Customer findOne(Specification<Customer> spec);
}
