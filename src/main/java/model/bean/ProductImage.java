package main.java.model.bean;

import java.io.Serializable;

import main.java.model.bean.Product;

public class ProductImage implements Serializable {

    private String type;
    private Product product;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
