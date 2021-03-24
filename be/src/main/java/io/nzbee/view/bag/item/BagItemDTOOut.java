package io.nzbee.view.bag.item;

public class BagItemDTOOut {

	private String itemUPC;
	
	private String itemDesc;
	
	private int itemQty;
	
	private String locale;
	
	private String currency;
	
	private Double markdownPrice;
	
	private Double bagItemTotal;
	
	private Double bagItemWeight;
	

	public BagItemDTOOut() {
		
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
	
	public double getMarkdownPrice() {
		return markdownPrice;
	}

	public void setMarkdownPrice(Double markdownPrice) {
		this.markdownPrice = markdownPrice;
	}

	public double getBagItemTotal() {
		return bagItemTotal;
	}

	public void setBagItemTotal(Double bagItemTotal) {
		this.bagItemTotal = bagItemTotal;
	}

	public Double getBagItemWeight() {
		return bagItemWeight;
	}

	public void setBagItemWeight(Double bagItemWeight) {
		this.bagItemWeight = bagItemWeight;
	}
	
}
