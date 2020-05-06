package io.nzbee.domain.customer;

import io.nzbee.domain.IService;
import io.nzbee.dto.customer.CustomerDTO;

public interface ICustomerService extends IService<Customer> {

	boolean customerExist(final String username);

	Customer findByUsername(String userName);

	void registerNewCustomer(final CustomerDTO customer);
}
