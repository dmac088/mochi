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
	public Customer registerNewCustomer(CustomerDTO customer) {		
		return customerService.registerNewCustomer(customer);
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

	@Override
	public void addCustomerLocation(Customer c, String clientIP) {
		customerService.addCustomerLocation(c, clientIP);
	}
	
	@Override
    public String validateVerificationToken(String token) {
		return customerService.validateVerificationToken(token);
	}
	
	@Override
	public Customer getCustomer(String token) {
		return customerService.getCustomer(token);
	}

	@Override
	public void authWithoutPassword(Customer customer) {
		customerService.authWithoutPassword(customer);
	}

}