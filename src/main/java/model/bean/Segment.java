package main.java.model.bean;

import java.util.List;

public class Segment {

	private String name;
	private int id;
	private List<Category> categorys;
	private List<List<Category>> categorysByRow;
	
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
	public List<List<Category>> getCategorysByRow() {
		return categorysByRow;
	}
	public void setCategorysByRow(List<List<Category>> categorysByRow) {
		this.categorysByRow = categorysByRow;
	}
	
	
}
