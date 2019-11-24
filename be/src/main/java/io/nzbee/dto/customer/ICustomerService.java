package io.nzbee.dto.customer;
import java.util.List;
import io.nzbee.dto.IService;

public interface ICustomerService extends IService<Customer,
												  io.nzbee.entity.party.Party,
												  io.nzbee.domain.customer.Customer> {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input
	 //The domain model is managed within the method
	 void registerNewCustomer(final Customer customer);
	 
	 List<Customer> getCustomers();
	 
	 void deleteCustomer(final Customer customer);
	 
	 Customer convertToCustomerDO(final io.nzbee.entity.party.Party party);
	 
	 void updateCustomer(Customer customer);

	 List<Customer> findAll();

	 Customer findByCode(String userName);
}
