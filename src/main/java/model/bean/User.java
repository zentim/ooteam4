package main.java.model.bean;

import java.io.Serializable;

import main.java.pattern.observer.Observer;
import main.java.pattern.observer.Subject;

/**
 * 
 * Observer Pattern - concrete observer
 *
 */
public class User implements Observer, Serializable {
	private int id;
	private String email;
	private String password;
	
	@Override
	public void update(Subject subject) {
		Product product = (Product) subject;
		System.out.println("User "+ id + " get a notify from Product " + product.getId() + ": ");
		System.out.println("Product is available now you can buy it. (Inventory Quantity: " + product.getInventory() + ")");
	}

	/* Getter and Setter */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
