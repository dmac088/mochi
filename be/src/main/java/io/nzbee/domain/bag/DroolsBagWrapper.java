package io.nzbee.domain.bag;


public class DroolsBagWrapper {
	
	private Bag bag;
	
	public DroolsBagWrapper(Bag bag) {
		this.bag = bag;
	}
	
	public void logItemError(String key, BagItem bag) {
		bag.getBag().logItemError(key, bag);
	}
	
	

}
