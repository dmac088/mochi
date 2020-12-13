package io.nzbee.domain.bag;

import java.util.List;
import java.util.stream.Collectors;

public class DroolsBagItemWrapper {
	
	private BagItem bagItem;
	
	public DroolsBagItemWrapper(BagItem bagItem) {
		this.bagItem = bagItem;
	}
	
	public int getBagItemQuantity() {
		return this.bagItem.getQuantity();
	}
	
	public int getBagQuantity() {
		return this.bagItem.getBag().getTotalQuantity();
	}
	
	public String getProductDesc() {
		return this.bagItem.getProduct().getProductDesc();
	}
	
	public Double getMarkdownPrice() {
		return this.bagItem.getProduct().getProductMarkdown();
	}
	
	public String getBagItemStatus() {
		return this.bagItem.getBagItemStatus();
	}
	
	public List<String> getPromotionCodes() {
		return this.bagItem.getProduct().getPromotions().stream().map(p -> p.getPromotionCode()).collect(Collectors.toList());
	}
	
	public Boolean isInStock() {
		return this.bagItem.getProduct().isInStock();
	}
	
	public Boolean isErrors() {
		return this.bagItem.isErrors();
	}
	
	public void setErrors(Boolean errors) {
		this.bagItem.setErrors(errors);
	}

	public String getError() {
		return this.bagItem.getError();
	}

	public void setError(String error) {
		this.bagItem.setError(error);
	}
	
	public String getCustomerId() {
		return bagItem.getBag().getCustomer().getCustomerID();
	}
	
	public void addBagItemDiscount(Double amount) {
		this.bagItem.addBagItemDiscount(amount);
	}
	
	public void logItemError(String key, BagItem bagItem) {
		bagItem.getBag().logItemError(key, bagItem);
	}

	public BagItem getBagItem() {
		return bagItem;
	}
	
	public Bag getBag() {
		return bagItem.getBag();
	}
	
}
