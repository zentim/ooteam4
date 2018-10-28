package main.java.pattern.memento;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import main.java.model.bean.OrderItem;
import main.java.model.util.DeepCloneUtil;


/**
 * 
 * Memento Pattern - Originator
 *
 */
public class ShoppingCart implements Serializable {

	private List<OrderItem> orderItems;
	
	public Memento createMemento() throws ClassNotFoundException, IOException {
		Memento memento = new Memento();
		List<OrderItem> ois = DeepCloneUtil.deepCloneList(orderItems);
		memento.setOrderItems(ois);
		
		return memento;
	}
	
	public void restoreFromMemento(Memento memento) {
		orderItems = memento.getOrderItems();
	}
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
