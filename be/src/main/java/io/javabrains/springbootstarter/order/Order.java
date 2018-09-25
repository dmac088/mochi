package io.javabrains.springbootstarter.order;

import java.util.ArrayList;
import io.javabrains.springbootstarter.orderline.OrderLine;
import io.javabrains.springbootstarter.thing.Thing;

//@Entity
public final class Order extends Thing {
	
	private ArrayList<OrderLine> orderlines; 
	
	public Order() {
		super();
	}
	
	public Order(int id)  {
		super(id);
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
