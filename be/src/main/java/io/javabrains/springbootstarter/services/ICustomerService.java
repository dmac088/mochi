package io.javabrains.springbootstarter.services;
import java.util.List;

public interface ICustomerService {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input
	 //The domain model is managed within the method
	 void registerNewCustomer(final CustomerDTO customer);
	 
	 
	 List<CustomerDTO> getCustomers();
	 
	 CustomerDTO getCustomer(String userName);
}
