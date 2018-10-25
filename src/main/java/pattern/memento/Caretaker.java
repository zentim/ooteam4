package main.java.pattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Memento Pattern - Caretaker
 *
 */
public class Caretaker {

	private List<Memento> mementos = new ArrayList<Memento>();

	public Memento getMemento() {
		int index = mementos.size() - 1;
		return mementos.get(index);
	}

	public void saveMemento(Memento memento) {
		mementos.add(memento);
	}

}
