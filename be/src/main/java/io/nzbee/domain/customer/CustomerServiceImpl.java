package io.nzbee.domain.customer;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	io.nzbee.entity.ports.ICustomerPortService customerService;
	
	@Override
	public Optional<Customer> findByCode(String userName) {
		// TODO Auto-generated method stub
		return customerService.findByCode(userName);  
	}

	@Override
	public Customer findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean customerExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerNewCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}