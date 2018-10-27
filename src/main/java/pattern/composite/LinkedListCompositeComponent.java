package main.java.pattern.composite;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;




/**
 * 
 * CompositeComponent - Category
 *
 */
public class LinkedListCompositeComponent extends Component {
	private Component component;
	private int id;
	private String name;
	private List<Component> components = new LinkedList<Component>();
	
	public LinkedListCompositeComponent(Component component) {
		this.id = component.getId();
		this.name = component.getName();
	}

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

}
