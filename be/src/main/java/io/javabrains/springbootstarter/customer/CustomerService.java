package io.javabrains.springbootstarter.customer;

import io.javabrains.springbootstarter.party.Party;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository; 

	public ArrayList<Party> getAllCustomers() {
		Iterator<Customer> i = customerRepository.findAll().iterator();
		ArrayList<Party> c1 = new ArrayList<>();
		while(i.hasNext()) {
			c1.add(i.next().getRoleParty());
		}
		return c1;
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
