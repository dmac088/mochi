package io.nzbee.domain.bag;

import java.util.List;
import java.util.stream.Collectors;

public class DroolsBagItemWrapper {
	
	private BagItem bagItem;
	
	private int bagItemQuantity;
	
	private String bagItemStatus;
	
	private int bagQuantity;
	
	private String productDesc;
	
	private double markdownPrice;
	
	private List<String> promotionCodes;
	
	private Boolean inStock;
	
	private Boolean errors;
	
	private String error;
	
	private String customerId;
	
	public DroolsBagItemWrapper(BagItem bagItem) {
		this.bagItem 			= bagItem;
		this.bagItemQuantity 	= bagItem.getQuantity();
		this.bagQuantity 		= bagItem.getBag().getTotalQuantity();
		this.productDesc 		= bagItem.getProduct().getProductDesc();
		this.markdownPrice 		= bagItem.getProduct().getProductMarkdown();
		this.bagItemStatus 		= bagItem.getBagItemStatus();
		this.promotionCodes 	= bagItem.getProduct().getPromotions().stream().map(p -> p.getPromotionCode()).collect(Collectors.toList());
		this.inStock 			= bagItem.getProduct().isInStock();
		this.customerId			= bagItem.getBag().getCustomer().getCustomerID();
		this.errors				= bagItem.isErrors();
		this.error 				= bagItem.getError();
	}
	
	public int getBagItemQuantity() {
		return this.bagItemQuantity;
	}
	
	public int getBagQuantity() {
		return this.bagQuantity;
	}
	
	public String getProductDesc() {
		return this.productDesc;
	}
	
	public Double getMarkdownPrice() {
		return this.markdownPrice;
	}
	
	public String getBagItemStatus() {
		return this.bagItemStatus;
	}
	
	public List<String> getPromotionCodes() {
		return this.promotionCodes;
	}
	
	public Boolean isInStock() {
		return this.inStock;
	}
	
	public Boolean isErrors() {
		return this.errors;
	}
	
	public void setErrors(Boolean errors) {
		this.errors = errors;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.bagItem.setError(error);
	}
	
	public String getCustomerId() {
		return this.customerId;
	}
	
}
