package main.java.model.bean;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import main.java.pattern.composite.Component;

/**
 * 
 * Composite Pattern - CompositeComponent
 *
 */
public class Brand extends Component implements Serializable {

    private String name;
    private int id;
    private Category category;
    private List<Product> products;
    private Hashtable<Integer, Component> components = new Hashtable<>();
    
    
    @Override
	public void add(Component c) {
		components.put(c.getId(), c);
	}

	@Override
	public void remove(Component c) {
		components.remove(c.getId());
	}

	@Override
	public Component getChild(int id) {
		return components.get(id);
	}

	@Override
	public void operation() {
		System.out.println("-- id: " + id + ", name: " + name);
		
		Set<Integer> keys = components.keySet();
		Iterator iterator = keys.iterator();
		while(iterator.hasNext()) {
			(components.get(iterator.next())).operation();
		}
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
    
	
	
	
    /* Getter and Setter */

	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
    

}
