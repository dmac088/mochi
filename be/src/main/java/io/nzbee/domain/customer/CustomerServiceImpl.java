package io.nzbee.domain.customer;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.dto.customer.CustomerDTO;

public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerPortService customerService;

	@Override
	public boolean customerExist(String username) {
		return false;
	}

	@Override
	public void registerNewCustomer(CustomerDTO customer) {		
		customerService.registerNewCustomer(customer);
	}

	@Override
	public void save(Customer object) {
		customerService.save(object);
	}

	@Override
	public void delete(Customer object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Customer> findByUsername(String userName) {
		return customerService.findByUsername(userName);
	}

	

}