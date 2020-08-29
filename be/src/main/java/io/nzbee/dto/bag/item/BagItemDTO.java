package io.nzbee.dto.bag.item;

public class BagItemDTO {

	private String itemUPC;
	
	private String itemDesc;
	
	private int qty;
	
	private String locale;
	
	private String currency;

	public BagItemDTO() {
		
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public void setItemUPC(String itemUPC) {
		this.itemUPC = itemUPC;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
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
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
