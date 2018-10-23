package main.java.pattern.composite;

import java.util.Hashtable;

/**
 * 
 * CompositeComponent - Brand
 *
 */
public class HashTableCompositeComponent extends Component {
	private int id;
	private Hashtable<Integer, Component> components = new Hashtable<Integer, Component>();
	
	public HashTableCompositeComponent(int id) {
		this.id = id;
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
		// TODO Auto-generated method stub
		super.operation();
	}

	@Override
	public int getId() {
		return id;
	}

}
