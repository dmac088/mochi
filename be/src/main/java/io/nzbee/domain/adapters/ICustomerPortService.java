package io.nzbee.domain.adapters;

import java.util.Set;
import io.nzbee.domain.customer.Customer;

public interface ICustomerPortService  extends IPortService<Customer> {

	Set<Customer> findAll(String locale, String currency, String category);

}
