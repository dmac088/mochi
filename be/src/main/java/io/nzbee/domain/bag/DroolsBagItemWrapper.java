package io.nzbee.domain.bag;

public class DroolsBagItemWrapper {

	private BagItem bagItem;
	
	public DroolsBagItemWrapper(BagItem bagItem) {
		this.bagItem = bagItem;
	}
	
	public int getBagItemQuantity() {
		return this.bagItem.getQuantity();
	}
	
	
}
