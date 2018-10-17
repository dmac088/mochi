package io.javabrains.springbootstarter.domain;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface CustomerRepository extends RoleBaseRepository<Customer> {

	
	List<Customer> findAll();
	
	//@Override
	Customer findOne(Long Id);
	
	//Customer findOneByCustomerId(String Id);
	//need to use specification since findByCustomerId causes an exception
	Customer findOne(Specification<Customer> spec);
	

}
