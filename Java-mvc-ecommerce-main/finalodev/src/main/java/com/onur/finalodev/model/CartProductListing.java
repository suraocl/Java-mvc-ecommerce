package com.onur.finalodev.model;

public class CartProductListing {

    private int quantity;
    private Product product;

    public CartProductListing() {
    }
    
    public CartProductListing(Product product2, int quantity2) {
    	this.quantity = quantity2;
    	this.product = product2;
	}

	public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
