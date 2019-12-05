package com.example.easyshop;

public class Product {

    int id;
    public String title;
    public String image;
    public String description;
    public double price;

    public Product(int _id, String _title, String _description, double _price,String _image) {
        this.id = _id;
        this.title = _title;
        this.description = _description;
        this.price = _price;
        this.image = _image;
    }

    public Product() {
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

    public void setPrice(double prix) {
        this.price = prix;

    }

    public void setProductImage(String link_image) {
        this.image = link_image;
    }


}