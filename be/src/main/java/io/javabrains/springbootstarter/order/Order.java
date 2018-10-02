package io.javabrains.springbootstarter.order;

import java.util.ArrayList;
import io.javabrains.springbootstarter.orderline.OrderLine;

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
