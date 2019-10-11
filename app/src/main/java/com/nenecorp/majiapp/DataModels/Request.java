package com.nenecorp.majiapp.DataModels;

public class Request {
    public static String P = "PENDING", F = "CANCELLED", T = "CONFIRMED";
    private String location;
    private int quantity;
    private String status;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    private double price;

    public String getLocation() {
        return location;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Request(String location) {
        this.location = location;
    }
}
