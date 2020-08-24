package io.nzbee.domain.bag;

import java.util.HashSet;
import java.util.Set;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;

public class Bag {
	
	private Set<BagItem> bagItems;
	private BagStatus bagStatus;
	private Customer customer;

	public Bag(Customer customer) {
		this.customer = customer;
		bagItems = new HashSet<BagItem>();
		bagStatus = BagStatus.NEW;
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
	
	public void removeItem(BagItem bi) {
		bagItems.remove(bi);
	}
	
	public BagStatus getBagStatus() {
		return bagStatus;
	}

}
