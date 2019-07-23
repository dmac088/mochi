package io.nzbee.domain.services.customer;
import java.util.List;

import io.nzbee.domain.Customer;

public interface ICustomerService {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input
	 //The domain model is managed within the method
	 void registerNewCustomer(final Customer customer);
	 
	 List<Customer> getCustomers();
	 
	 Customer getCustomer(String userName);
	 
	 void deleteCustomer(final Customer customer);
	 
	 Customer convertToCustomerDO(final io.nzbee.entity.party.Party party);
	 
	 void updateCustomer(Customer customer);
}
