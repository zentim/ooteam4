package main.java.pattern.composite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * 
 * CompositeComponent - Segment
 *
 */
public class TreeCompositeComponent extends Component {
	private Component component;
	private int id;
	private String name;
	private Map<Integer, Component> components = new TreeMap<Integer, Component>();
	
	public TreeCompositeComponent(Component component) {
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
		System.out.println("Segment id: " + id + ", name: " + name);
		
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
