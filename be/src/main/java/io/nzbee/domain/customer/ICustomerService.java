package io.nzbee.domain.customer;

import java.util.Optional;
import io.nzbee.domain.IService;
import io.nzbee.dto.customer.CustomerDTO;

public interface ICustomerService extends IService<Customer> {

	boolean customerExist(final String username);

	Optional<Customer> findByUsername(String userName);

	void registerNewCustomer(final CustomerDTO customer);
}
