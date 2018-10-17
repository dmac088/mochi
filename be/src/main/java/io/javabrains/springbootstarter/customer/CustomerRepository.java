package io.javabrains.springbootstarter.customer;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import io.javabrains.springbootstarter.role.RoleBaseRepository;

@Repository("customerRepository")
public interface CustomerRepository extends RoleBaseRepository<Customer> {

	
	List<Customer> findAll();
	
	//@Override
	Customer findOne(Long Id);
	
	//Customer findOneByCustomerId(String Id);
	//need to use specification since findByCustomerId causes an exception
	Customer findOne(Specification<Customer> spec);
	

}
