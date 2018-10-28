package main.java.pattern.memento;

import java.io.Serializable;
import java.util.List;

import main.java.model.bean.OrderItem;

/**
 * 
 * Memento Pattern - Memento
 *
 */
public class Memento implements Serializable {
	private List<OrderItem> orderItems;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	

}
