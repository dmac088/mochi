package io.nzbee.domain.bag;

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
	
	public String getBagItemStatus() {
		return bagItem.getBagItemStatus();
	}
}
