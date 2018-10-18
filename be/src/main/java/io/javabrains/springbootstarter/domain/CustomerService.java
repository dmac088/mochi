package io.javabrains.springbootstarter.domain;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomCustomerRepository customerRepository; 

	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@PreAuthorize("hasAuthority('CUSTOMER_READ')")
	public Customer getCustomer(Long CustomerId) {
		return customerRepository.findOne(CustomerId);
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
	public void updateCustomer(Long id, Customer customer) {
		customerRepository.save(customer);
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
	public void deleteCustomer(Long id) {
		customerRepository.delete(id);
	}
	
	
}
