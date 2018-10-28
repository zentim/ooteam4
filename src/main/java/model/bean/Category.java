package main.java.model.bean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import main.java.pattern.composite.Component;

/**
 * 
 * Composite Pattern - CompositeComponent
 *
 */
public class Category extends Component implements Serializable {

	private String name;
	private int id;
	private Segment segment;
	private List<Brand> brands;
	private List<Component> components = new LinkedList<>();
	
	
	@Override
	public void add(Component c) {
		components.add(c);
	}

	@Override
	public void remove(Component c) {
		components.remove(c);
	}

	@Override
	public Component getChild(int id) {
		for(Component c : components) {
			if (c.getId() == id) {
				return c;
			}
		}
		
		return null;
	}

	@Override
	public void operation() {
		System.out.println("- id: " + id + ", name: " + id);
		
		Iterator iterator = components.iterator();
		while(iterator.hasNext()) {
			((Component)iterator.next()).operation();
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
	public Segment getSegment() {
		return segment;
	}
	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	
}
