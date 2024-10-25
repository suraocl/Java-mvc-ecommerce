package com.onur.finalodev.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Order {

    private int id;
    private int userId;
    private double totalPrice;
    private LocalDateTime createdAt;
    private String address;
    private int paymentMethodId;

    private String status;
    
    public Order() {
    }

    public Order(int id, int userId, double totalPrice, LocalDateTime createdAt, String address) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public int getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
