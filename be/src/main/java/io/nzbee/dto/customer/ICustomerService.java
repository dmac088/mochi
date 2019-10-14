package io.nzbee.dto.customer;
import java.util.List;
import java.util.Optional;
import io.nzbee.dto.IService;

public interface ICustomerService extends IService<Customer> {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input
	 //The domain model is managed within the method
	 void registerNewCustomer(final Customer customer);
	 
	 List<Customer> getCustomers();
	 
	 void deleteCustomer(final Customer customer);
	 
	 Customer convertToCustomerDO(final io.nzbee.entity.party.Party party);
	 
	 void updateCustomer(Customer customer);

	 List<Customer> findAll();

	 Optional<Customer> findByCode(String userName);
}
