package io.nzbee.entity.role.customer;

import java.util.Optional;

public interface ICustomerService {

	Optional<CustomerEntity> findByUsername(String userName);
	
	Optional<CustomerEntity> findByCustomerNumber(String customerNumber);
	
}
