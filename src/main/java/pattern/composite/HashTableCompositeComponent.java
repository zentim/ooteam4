package main.java.pattern.composite;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * CompositeComponent - Brand
 *
 */
public class HashTableCompositeComponent extends Component {
	private Component component;
	private int id;
	private String name;
	private Hashtable<Integer, Component> components = new Hashtable<Integer, Component>();
	
	public HashTableCompositeComponent(Component component) {
		this.id = component.getId();
		this.name = component.getName();
	}

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
			((Component)components.get(iterator.next())).operation();
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
