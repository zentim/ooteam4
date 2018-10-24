package main.java.pattern.facade;

import java.util.List;

import main.java.model.bean.OrderItem;
import main.java.model.dao.OrderItemDAO;

public class ClearProduct {

	public ClearProduct() {
		System.out.println("Prepare ClearProduct...");
	}
	
	public void doClear(List<OrderItem> ois) {
		OrderItemDAO orderItemDAO = new OrderItemDAO();
		
		for (OrderItem oi : ois) {
			orderItemDAO.delete(oi.getId());
		}
	}

}
