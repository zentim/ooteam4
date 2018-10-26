package main.java.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.pattern.composite.Component;
import main.java.pattern.observer.Observer;
import main.java.pattern.observer.Subject;

/**
 * 
 * Observer Pattern - concrete subject
 * Composite Pattern - leaf component
 *
 */
public class Product extends Component implements Subject, Serializable {
	private int id;
	private String name;
	private float price;
	private int inventory;
	private Date dateAdded;
	private Brand brand;
	private ProductImage firstProductImage;
	private List<ProductImage> productImages;
	private List<ProductImage> productSingleImages;
	private List<ProductImage> productDetailImages;
	private int discountType = -1;
	private String discountTypeName;
	private String promotionName;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	/* Observer Methods */
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	@Override
	public void notifyObservers() {
		for(Observer o : observers) {
			o.update(this);
		}
	}
	
	public void setInventory(int inventory) {
		if (this.inventory == 0 && inventory > 0) {
			this.inventory = inventory;
			notifyObservers();
		} else {
			this.inventory = inventory;
		}
	}
	
	/* Composite Method */
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
	
	
	
	/* Getter and Setter */
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getInventory() {
		return inventory;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public ProductImage getFirstProductImage() {
		return firstProductImage;
	}
	public void setFirstProductImage(ProductImage firstProductImage) {
		this.firstProductImage = firstProductImage;
	}
	public List<ProductImage> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}
	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}
	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}
	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}
	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}
	public int getDiscountType() {
		return discountType;
	}
	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}
	public String getDiscountTypeName() {
		return discountTypeName;
	}
	public void setDiscountTypeName(String discountTypeName) {
		this.discountTypeName = discountTypeName;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
}
