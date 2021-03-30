package io.nzbee.domain.bag;


public class DroolsBagWrapper {
	
	private Bag bag;
	
	private String couponCode;
	
	public DroolsBagWrapper(Bag bag) {
		this.bag = bag;
		this.couponCode = bag.getCouponCode();
	}
	
	public void logItemError(String key, BagItem bag) {
		bag.getBag().logItemError(key, bag);
	}
	
	public int getTotalItems() {
		return bag.getTotalItems();
	}
	
	public Double getTotalAmount() {
		return bag.getTotalAmount();
	}

	public String getCouponCode() {
		return couponCode;
	}

}
