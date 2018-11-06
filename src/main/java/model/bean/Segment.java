package main.java.model.bean;

import java.io.Serializable;
import java.util.List;

public class Segment implements Serializable {

	private String name;
	private int id;
	private List<Category> categorys;	
	
	/* Getter and Setter */
	public String getName() {
        return name;
    }
	public void setName(String name) {
		this.name = name;
	}
    public int getId() {
        return id;
    }	
	public void setId(int id) {
		this.id = id;
	}
	public List<Category> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}
	
	
	
}
