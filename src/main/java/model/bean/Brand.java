package main.java.model.bean;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import main.java.pattern.composite.Component;
import main.java.pattern.decorator.PrintPromotionInfoDecorator;

/**
 * 
 * Composite Pattern - Composite
 * Iterator Pattern - Client
 *
 */
public class Brand extends Component {

    private String name;
    private int id;
    private Category category;
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
		System.out.println("     bid: " + id + ", name: " + name);
		
		/**
		 * Use Iterator Pattern
		 */
		Set<Integer> keys = components.keySet();
		Iterator iterator = keys.iterator();
		while(iterator.hasNext()) {
		    Product p = ((Product) components.get(iterator.next()));
		    /**
		     * Use Decorator Pattern
		     */
		    new PrintPromotionInfoDecorator(p).operation();
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

    public Hashtable<Integer, Component> getComponents() {
        return components;
    }

    public void setComponents(Hashtable<Integer, Component> components) {
        this.components = components;
    }

}