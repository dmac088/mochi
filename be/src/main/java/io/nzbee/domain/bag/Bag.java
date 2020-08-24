package io.nzbee.domain.bag;

import io.nzbee.domain.customer.Customer;

public class Bag {
	
	private Customer customer;

	public Bag(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
	
}
