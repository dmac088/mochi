package io.nzbee.entity.role.customer;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Override
	public Optional<CustomerEntity> findByUsername(String userName) {
		return customerRepository.findByRolePartyPartyUserUsername(userName);
	}

	@Override
	public Optional<CustomerEntity> findByCustomerNumber(String customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber);
	}

}
