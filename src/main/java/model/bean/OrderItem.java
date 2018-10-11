package main.java.model.bean;

import main.java.model.bean.Order;
import main.java.model.bean.Product;
import main.java.model.bean.User;

public class OrderItem {
	private int id;
	private int quantity;
	private int state;
	private float originalPrice;
	private float promotionalPrice;
	private Product product;
	private Order order;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getPromotionalPrice() {
		return promotionalPrice;
	}

	public void setPromotionalPrice(float promotionalPrice) {
		this.promotionalPrice = promotionalPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", state=" + state + ", originalPrice="
				+ originalPrice + ", promotionalPrice=" + promotionalPrice + ", product=" + product + ", order=" + order
				+ ", user=" + user + "]";
	}

	
}
