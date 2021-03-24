package io.nzbee.domain.bag;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;

public class Bag {
	
	private Set<BagItem> bagItems = new HashSet<>();
	
	private Customer customer;
	
	private BagIssues bagIssues = new BagIssues();

	public Bag(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Set<BagItem> getBagItems() {
		return bagItems;
	}
	
	public Set<BagItem> getBagItemsByType(Class<?> type) {
		return bagItems.stream().filter(i -> i.getClass().equals(type)).collect(Collectors.toSet());
	}
	
	public void addItem(Product p, int qty) {
		System.out.println("adding quantity " + qty + " for product " + p.getProductUPC());
		
		Optional<BagItem> obi = this.getBagItems().stream()
		.filter(bi -> bi.getProduct().getProductUPC().equals(p.getProductUPC()))
		.findAny();
		
		System.out.println("bagItem found = " + obi.isPresent());
		
		if(obi.isPresent()) {
			System.out.println("addToQuantity");
			obi.get().addToQuantity(qty);
		} else {
			System.out.println("adding bag item");
			this.getBagItems().add(new BagItem(this, p, qty));
		}
		
	}
	
	public boolean bagItemExists(String productUPC) {
		return this.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals(productUPC)).findAny().isPresent();
	}
	
	public BagItem getBagItem(String productUPC) {
		return this.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals(productUPC)).findAny().get();
	}
	
	public void addItem(BagItem bi) {
		this.addItem(bi.getProduct(), bi.getQuantity());
	}
	
	public void removeItem(BagItem bi) {
		this.getBagItems().remove(bi);
	}
	
	public int getTotalItems() {
		return this.getBagItems().size();
	}
	
	
	public int getTotalQuantity() {
		return this.getBagItems().stream().mapToInt(BagItem::getQuantity).sum();
	}
	
	public Double getTotalWeight() {
		return this.getBagItemsByType(PhysicalProduct.class).stream().mapToDouble(bi -> bi.getQuantity() * ((PhysicalProduct) bi.getProduct()).getWeight()).sum();
	}
	
	public Double getTotalAmount() {
		return this.getBagItems().stream().mapToDouble(bi -> bi.getQuantity() * bi.getProduct().getProductMarkdown()).sum();
	}
	
	public void logItemError(String key, BagItem bagItem) {
		bagIssues.logItemError(key, bagItem);
		bagItem.setBagItemStatus("PND01");
	}
	
	public BagIssues getBagIssues() {
		return bagIssues;
	}

	public boolean hasIssues() {
		return bagIssues.hasIssues();
	}
}
