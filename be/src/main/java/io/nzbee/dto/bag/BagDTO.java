package io.nzbee.dto.bag;

public class BagDTO {

	private String bagStatusCode;
	
	private String bagStatusDesc;
	
	private int totalItems;
	
	private int totalQuantity;

	public String getBagStatusCode() {
		return bagStatusCode;
	}

	public void setBagStatusCode(String bagStatusCode) {
		this.bagStatusCode = bagStatusCode;
	}

	public String getBagStatusDesc() {
		return bagStatusDesc;
	}

	public void setBagStatusDesc(String bagStatusDesc) {
		this.bagStatusDesc = bagStatusDesc;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	
}
