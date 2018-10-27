package main.java.pattern.composite;




/**
 * 
 * Leaf - Product
 *
 */
public class PrimitiveComponent extends Component {
	private Component component;
	private int id;
	private String name;
	
	public PrimitiveComponent(Component component) {
		this.id = component.getId();
		this.name = component.getName();
	}

	@Override
	public void operation() {
		System.out.println("--- id: " + id + ", name: " + name);
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
