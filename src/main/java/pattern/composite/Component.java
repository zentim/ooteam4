package main.java.pattern.composite;

/**
 * 
 * Composite Pattern (Transparency) - Component
 *
 */
public abstract class Component {
    
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