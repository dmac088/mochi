package io.nzbee.domain.bag;

import java.util.ArrayList;
import java.util.List;

import io.nzbee.Constants;
import io.nzbee.domain.bag.discount.Discount;
import io.nzbee.domain.product.Product;

public class BagItem {

	private Bag bag;
	
	private Product product;
	
	private int quantity;
	
	private String locale;
	
	private String currency;
	
	private boolean errors;
	
	private String error;
	
	private String bagItemStatus;
	
	private Double bagItemDiscount;
	
	private List<Discount> discounts;
	
	public BagItem(Bag bag, 
			  	   Product p, 
			  	   int quantity) {
		this.bag 				= bag;
		this.product 			= p;
		this.quantity 			= quantity;
		this.locale 			= p.getLclCd();
		this.currency 			= p.getCurrency();
		this.bagItemStatus 		= Constants.bagStatusCodeNew;
		this.bagItemDiscount	= new Double(0);
		this.discounts 			= new ArrayList<Discount>();
	}

	public Bag getBag() {
		return bag;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void addToQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public String getLocale() {
		return this.locale;
	}

	public String getCurrency() {
		return this.currency;
	}
	
	public Double getBagItemTotal() {
		return (this.quantity * this.product.getProductMarkdown()) - this.bagItemDiscount;
	}

	public boolean isErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public void setBagItemStatus(String bagItemStatus) {
		this.bagItemStatus = bagItemStatus;
	}

	public String getBagItemStatus() {
		return bagItemStatus;
	}
	
	public Double getBagItemDiscount() {
		return bagItemDiscount;
	}

	public void addBagItemDiscount(Double amount) {
		this.bagItemDiscount += amount;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}
	
	
}
