package main.java.pattern.facade;

import java.util.List;

import main.java.model.bean.OrderItem;

public class ClearOrderFacade {
	List<OrderItem> ois;
	ClearProduct clearProduct;
	ClearPayment clearPayment;
	ClearInvoice clearInvoice;

	public ClearOrderFacade(List<OrderItem> ois) {
		this.ois = ois;
		clearProduct = new ClearProduct();
		clearPayment = new ClearPayment();
		clearInvoice = new ClearInvoice();
	}
	
	public void operation() {
		clearProduct.doClear(ois);
		
		System.out.println("Finished ClearOrder...");
	}

}
