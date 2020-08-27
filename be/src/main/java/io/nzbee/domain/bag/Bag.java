package io.nzbee.domain.bag;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;

public class Bag {
	
	@JsonManagedReference
	private Set<BagItem> bagItems;
	private BagStatus bagStatus;
	
	@JsonIgnore
	private Customer customer;

	public Bag(Customer customer) {
		this.customer 	= customer;
		bagItems 		= new HashSet<BagItem>();
		bagStatus 		= BagStatus.NEW;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public Set<BagItem> getBagItems() {
		return bagItems;
	}
	
	public void addItem(Product p, int qty) {
		Optional<BagItem> obi = this.getBagItems().stream()
		.filter(bi -> bi.getProduct().getProductUPC().equals(p.getProductUPC()))
		.findFirst();
		
		BagItem bi = new BagItem(this, p, qty);
		
		if(obi.isPresent()) {
			bi = obi.get();
			bi.addToQuantity(qty);
		} else {
			this.getBagItems().add(bi);
		}
	}
	
	public void removeItem(BagItem bi) {
		this.getBagItems().remove(bi);
	}
	
	public BagStatus getBagStatus() {
		return bagStatus;
	}

}
