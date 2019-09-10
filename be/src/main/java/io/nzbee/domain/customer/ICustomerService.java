package io.nzbee.domain.customer;
import java.util.List;

import io.nzbee.domain.services.IService;

public interface ICustomerService extends IService<Customer> {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input
	 //The domain model is managed within the method
	 void registerNewCustomer(final Customer customer);
	 
	 List<Customer> getCustomers();
	 
	 void deleteCustomer(final Customer customer);
	 
	 Customer convertToCustomerDO(final io.nzbee.entity.party.Party party);
	 
	 void updateCustomer(Customer customer);
}
