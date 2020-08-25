package io.nzbee.dto.bag.item;

public class BagItemDTO {

	private String bagUserName;
	
	private String itemUPC;
	
	private int qty;
	
	private String locale;

	public BagItemDTO() {
		
	}

	public String getBagUserName() {
		return bagUserName;
	}

	public void setBagUserName(String bagUserName) {
		this.bagUserName = bagUserName;
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public void setItemUPC(String itemUPC) {
		this.itemUPC = itemUPC;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
}
