package main.java.pattern.composite;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import main.java.model.bean.Brand;
import main.java.model.dao.BrandDAO;

/**
 * 
 * CompositeComponent - Category
 *
 */
public class LinkedListCompositeComponent extends Component {
	private int id;
	private List<Component> components = new LinkedList<Component>();
	
	public LinkedListCompositeComponent(int id) {
		this.id = id;
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
		Brand c = new BrandDAO().get(id);
		System.out.println("- id: " + c.getId() + ", name: " + c.getName());
		
		Iterator iterator = components.iterator();
		while(iterator.hasNext()) {
			((Component)iterator.next()).operation();;
		}
	}

	@Override
	public int getId() {
		return id;
	}

}
