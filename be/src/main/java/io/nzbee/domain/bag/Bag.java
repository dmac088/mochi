package io.nzbee.domain.bag;

import java.time.LocalDateTime;
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
	private LocalDateTime createTime;
	private LocalDateTime updateTime;

	@JsonIgnore
	private Customer customer;

	public Bag(	Customer customer) {
		this.customer 		= customer;
		this.bagItems 		= new HashSet<BagItem>();
		this.bagStatus 		= BagStatus.NEW;
	}
	
	public Bag(	Customer customer,
				LocalDateTime createTime,
				LocalDateTime updateTime) {
		this.customer 		= customer;
		this.bagItems 		= new HashSet<BagItem>();
		this.bagStatus 		= BagStatus.NEW;
		this.createTime 	= createTime;
		this.updateTime 	= updateTime;
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
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
}
