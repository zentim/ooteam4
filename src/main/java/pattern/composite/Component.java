package main.java.pattern.composite;

import java.io.Serializable;

/**
 * 
 * Composite Pattern (Transparency)
 *
 */
public abstract class Component implements Serializable {
	
	public void add(Component c) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(Component c) {
		throw new UnsupportedOperationException();
	}
	
	public Component getChild(int id) { 
		throw new UnsupportedOperationException();
	}
	
	public void operation() {
		throw new UnsupportedOperationException();
	};
	
	public int getId() {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}

}
