package com.example.easyshop;

public class Product {

    int id;
    public String title;
    public String image;
    public String description;
    public double price;

    public Product(int id, String title, String description, double price) {
        new Product(id, title, description, price, "");
    }

    public Product(int id, String title, String description, double price, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return description;
    }

    public String getimage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductImage(String image) {
        this.image = image;
    }
}
