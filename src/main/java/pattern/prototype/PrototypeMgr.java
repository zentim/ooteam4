package main.java.pattern.prototype;

import main.java.model.bean.User;

/**
 * 
 * Prototype Pattern - PrototypeMgr
 * Singleton Pattern
 *
 */
public class PrototypeMgr {
    /* Use Singleton */
    private static User instance = new User();

    private PrototypeMgr() {}
    
    public static User getInstance() {
        return instance;
    }
    
    /* Prototype Method */
    public static User getPrototype() throws CloneNotSupportedException {
        return (User) instance.clone();
    }
    
}
