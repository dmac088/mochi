package io.nzbee.domain.customer;

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
		customerService.delete(object);
		
	}
	
	@Override
	public void update(Customer object) {
		customerService.update(object);
	}

	@Override
	public Customer findByUsername(String userName) {
		return customerService.findByUsername(userName);
	}

	@Override
	public void delete(String userName) {
		customerService.delete(userName);
		
	}

	@Override
	public void update(CustomerDTO customerDTO) {
		customerService.update(customerDTO);
	}

}