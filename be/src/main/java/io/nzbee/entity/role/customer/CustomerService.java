package io.nzbee.entity.role.customer;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Override
	public Optional<Customer> findByUsername(String userName) {
		return customerRepository.findByRolePartyPartyUserUsername(userName);
	}

	@Override
	public Optional<Customer> findByCustomerNumber(String customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber);
	}

}
