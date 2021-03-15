package io.nzbee.domain.bag;

import java.util.List;
import java.util.stream.Collectors;

import io.nzbee.domain.bag.discount.Discount;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.promotion.Promotion;

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
	
	public List<Promotion> getAllPromotions() {
		return this.bagItem.getProduct().getPromotions();
	}
	
	public Promotion getPromotion(String promotionCode) {
		return this.bagItem.getProduct().getPromotions().stream().filter(p -> p.getPromotionCode().equals(promotionCode)).findAny().get();
	}
	
	public Boolean isInStock() {
		return ((PhysicalProduct) this.bagItem.getProduct()).isInStock();
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
	
	public void addDiscount(Discount discount) {
		this.bagItem.addDiscount(discount);
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
