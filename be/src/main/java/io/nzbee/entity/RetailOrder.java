package io.nzbee.entity;

import java.util.ArrayList;

//@Entity
public final class RetailOrder {
	
	private ArrayList<RetailOrderLine> orderlines; 
	
	public RetailOrder() {
	}
	
	public ArrayList<RetailOrderLine> getOrderLines() {
		return orderlines;
	}
	
	public void addOrderLine(RetailOrderLine orderline) {
		this.orderlines.add(orderline);
	}
	
	public void removeOrderLine(RetailOrderLine orderline) {
		this.orderlines.remove(orderline);
	}
}
