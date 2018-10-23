package main.java.pattern.composite;

import main.java.model.bean.Product;
import main.java.model.dao.ProductDAO;

/**
 * 
 * Leaf - Product
 *
 */
public class PrimitiveComponent extends Component {
	private int id;
	
	public PrimitiveComponent(int id) {
		this.id = id;
	}

	@Override
	public void operation() {
		Product p = new ProductDAO().get(id);
		System.out.println("--- id: " + p.getId() + ", name: " + p.getName());
	}
	
	@Override
	public int getId() {
		return id;
	}
	
}
