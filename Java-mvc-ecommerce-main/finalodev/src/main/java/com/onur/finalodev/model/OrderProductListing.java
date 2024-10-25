package com.onur.finalodev.model;

public class OrderProductListing {

    private int quantity;
    private Product product;
    private Order order;
    
    public OrderProductListing(int quantity , Product product, Order order){
    	
    	this.order = order;
    	this.product = product;
    	this.quantity = quantity;
    	
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

}
