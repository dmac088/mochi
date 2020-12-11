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
	
	public DroolsBagItemWrapper(BagItem bagItem) {
		this.bagItemQuantity 	= bagItem.getQuantity();
		this.bagQuantity 		= bagItem.getBag().getTotalQuantity();
		this.productDesc 		= bagItem.getProduct().getProductDesc();
		this.markdownPrice 		= bagItem.getProduct().getProductMarkdown();
		this.bagItemStatus 		= bagItem.getBagItemStatus();
		this.promotionCodes 	= bagItem.getProduct().getPromotions().stream().map(p -> p.getPromotionCode()).collect(Collectors.toList());
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
}
