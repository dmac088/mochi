package io.javabrains.springbootstarter.customer;

import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, String> {

	//List<Customer> getAllCustomers();
	//Customer getCustomer(int id);
	//void updateCustomer(int id);
	//void deleteCustomer(int id);

}
