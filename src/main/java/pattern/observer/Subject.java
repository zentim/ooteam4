package main.java.pattern.observer;

/**
 * 
 * Observer Pattern - subject
 *
 */
public interface Subject {

    public void addObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers();
}
