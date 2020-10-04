package io.nzbee.entity.role.customer;

import java.util.Optional;

public interface ICustomerService {

	Optional<Customer> findByUsername(String userName);
	
	Optional<Customer> findByCustomerNumber(String customerNumber);
	
}
