package io.nzbee.domain.bag;

import java.util.HashSet;
import java.util.Set;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;

public class Bag {
	
	private Set<BagItem> bagItems = new HashSet<BagItem>();
	
	private Customer customer;

	public Bag(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public Set<BagItem> getBagItems() {
		return bagItems;
	}
	
	public void addItem(Product p, int qty) {
		BagItem BagItem = new BagItem(this, p, qty);
		bagItems.add(BagItem);
	}
	
}
