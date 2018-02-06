package io.javabrains.springbootstarter.customer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository; 

	public List<Customer> getAllCustomers() {
		Iterator<Customer> i = customerRepository.findAll().iterator();
		List<Customer> c1 = new ArrayList<>();
		while(i.hasNext()) {
			c1.add(i.next());
		}
		return c1;
	}
	
	public Customer getCustomer(String id) {
		return customerRepository.findOne(id);
	}
	
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public void updateCustomer(String id, Customer customer) {
		customerRepository.save(customer);
	}
	
	public void deleteCustomer(String id) {
		customerRepository.delete(id);
	}
	
}
