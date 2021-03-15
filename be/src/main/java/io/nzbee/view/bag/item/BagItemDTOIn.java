package io.nzbee.view.bag.item;

public class BagItemDTOIn {

	private String itemUPC;
	
	private int itemQty;

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
	
}
