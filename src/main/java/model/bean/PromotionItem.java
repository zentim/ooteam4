package main.java.model.bean;

import main.java.model.bean.Promotion;
import main.java.model.bean.Product;

public class PromotionItem {
	private int id;
	private int minQuantity;
	private int discountOf;
	private Promotion promotion;
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public int getDiscountOf() {
		return discountOf;
	}

	public void setDiscountOf(int discountOf) {
		this.discountOf = discountOf;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
