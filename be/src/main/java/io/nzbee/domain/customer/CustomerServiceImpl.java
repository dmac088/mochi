package io.nzbee.domain.customer;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	io.nzbee.domain.ports.ICustomerPortService customerService;
	

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
	public void save(Customer object) {
		customerService.save(object);
	}

	@Override
	public void delete(Customer object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Customer> findByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}