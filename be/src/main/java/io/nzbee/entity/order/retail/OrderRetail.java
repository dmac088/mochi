package io.nzbee.entity.order.retail;

import java.util.ArrayList;

import io.nzbee.entity.order.line.OrderLine;

//@Entity
public final class OrderRetail {
	
	private ArrayList<OrderLine> orderlines; 
	
	public OrderRetail() {
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
