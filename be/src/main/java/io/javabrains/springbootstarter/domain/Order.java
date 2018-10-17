package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;

//@Entity
public final class Order {
	
	private ArrayList<OrderLine> orderlines; 
	
	public Order() {
		super();
	}
	
	public ArrayList<OrderLine> getOrderLines() {
		return orderlines;
	}
	
	public void addOrderLine(OrderLine orderline) {
		this.orderlines.add(orderline);
	}
	
	public void removeOrderLine(OrderLine orderline) {
		this.orderlines.remove(orderline);
	}
}
