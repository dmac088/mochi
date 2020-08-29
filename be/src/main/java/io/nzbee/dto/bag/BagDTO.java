package io.nzbee.dto.bag;

import java.util.Set;

import io.nzbee.dto.bag.item.BagItemDTO;

public class BagDTO {

	private String customerUserName;

	private Set<BagItemDTO> bagItems;

	private String bagStatusCode;
	
	private String bagStatusDesc;
	
	private int totalItems;
	
	private int totalQuantity;
	
	
	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}
	
	public Set<BagItemDTO> getBagItems() {
		return bagItems;
	}

	public void setBagItems(Set<BagItemDTO> bagItems) {
		this.bagItems = bagItems;
	}

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
