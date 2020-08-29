package io.nzbee.dto.bag.item;

public class BagItemDTOIn {

	private String itemUPC;
	
	private int itemQty;
	
	private String locale;
	
	private String currency;

	public BagItemDTOIn() {
		
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public void setItemUPC(String itemUPC) {
		this.itemUPC = itemUPC;
	}

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int qty) {
		this.itemQty = qty;
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
