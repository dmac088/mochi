package io.nzbee.domain.ports;

import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.view.customer.CustomerDTOIn;

public interface ICustomerPortService extends IPortService<Customer> {

	public Customer findByUsername(String userName);
	
	Customer registerNewCustomer(CustomerDTOIn customer);

	void update(CustomerDTOIn dto);
	
	void delete(String userName);

	public void addCustomerLocation(Customer c, String clientIP);

	public String validateVerificationToken(String token);

	public Customer getCustomer(String token);

	void authWithoutPassword(String token);

	void addItemToBag(Customer c, BagItem bagItem);
}
