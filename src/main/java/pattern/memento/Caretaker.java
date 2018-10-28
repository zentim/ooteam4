package main.java.pattern.memento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Memento Pattern - Caretaker
 *
 */
public class Caretaker implements Serializable {

	private List<Memento> mementos = new ArrayList<>();

	public Memento getMemento() {
		int index = mementos.size() - 1;
		return mementos.get(index);
	}

	public void saveMemento(Memento memento) {
		mementos.add(memento);
	}

}
