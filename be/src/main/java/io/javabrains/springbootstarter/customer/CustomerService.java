package io.javabrains.springbootstarter.customer;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository; 

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomer(String CustomerId) {
		return customerRepository.findOne(CustomerSpecs.byCustomerID(CustomerId));
	}
	
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public void updateCustomer(Long id, Customer customer) {
		customerRepository.save(customer);
	}
	
}
