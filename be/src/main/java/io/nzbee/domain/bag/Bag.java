package io.nzbee.domain.bag;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;

public class Bag {
	
	private Set<BagItem> bagItems;
	private BagStatus bagStatus;
	
	@JsonIgnore
	private Customer customer;

	public Bag(Customer customer) {
		this.customer 	= customer;
		bagItems 		= new HashSet<BagItem>();
		bagStatus 		= BagStatus.NEW;
	}
	
	public Bag(Customer customer, Set<BagItem> bagItems, BagStatus bagStatus) {
		this.customer 	= customer;
		this.bagItems 	= bagItems;
		this.bagStatus 	= bagStatus;
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
	
	public void addItem(BagItem bi) {
		bagItems.add(bi);
	}
	
	public void removeItem(BagItem bi) {
		bagItems.remove(bi);
	}
	
	public BagStatus getBagStatus() {
		return bagStatus;
	}

}
