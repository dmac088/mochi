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
