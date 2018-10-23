package main.java.pattern.composite;

import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * CompositeComponent - Segment
 *
 */
public class TreeCompositeComponent extends Component {
	private int id;
	private Map<Integer, Component> components = new TreeMap<Integer, Component>();
	
	public TreeCompositeComponent(int id) {
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
