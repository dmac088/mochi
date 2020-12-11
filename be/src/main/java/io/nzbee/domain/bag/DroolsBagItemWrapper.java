package io.nzbee.domain.bag;

import java.util.List;
import java.util.stream.Collectors;

public class DroolsBagItemWrapper {

	private BagItem bagItem;
	
	private int bagItemQuantity;
	
	private int bagQuantity;
	
	public DroolsBagItemWrapper(BagItem bagItem) {
		this.bagItemQuantity = bagItem.getQuantity();
		this.bagQuantity = bagItem.getBag().getTotalQuantity();
	}
	
	public int getBagItemQuantity() {
		return this.bagItemQuantity;
	}
	
	public int getBagQuantity() {
		return this.bagQuantity;
	}
	
	public String getProductDesc() {
		return bagItem.getProduct().getProductDesc();
	}
	
	public Double getMarkdownPrice() {
		return this.bagItem.getProduct().getProductMarkdown();
	}
	
	public String getBagItemStatus() {
		return bagItem.getBagItemStatus();
	}
	
	public List<String> getPromotionCodes() {
		return this.bagItem.getProduct().getPromotions().stream().map(p -> p.getPromotionCode()).collect(Collectors.toList());
	}
}
