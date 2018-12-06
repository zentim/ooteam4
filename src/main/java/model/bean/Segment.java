package main.java.model.bean;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import main.java.pattern.composite.Component;

/**
 * 
 * Composite Pattern - Composite
 * Iterator Pattern - Client
 *
 */
public class Segment extends Component {

    private String name;
    private int id;
    private Map<Integer, Component> components = new TreeMap<>();

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

        /**
         * Use Iterator Pattern
         */
        Set<Integer> keys = components.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            components.get(iterator.next()).operation();
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

    public Map<Integer, Component> getComponents() {
        return components;
    }

    public void setComponents(Map<Integer, Component> components) {
        this.components = components;
    }

}