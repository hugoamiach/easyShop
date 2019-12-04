package com.example.easyshop;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Produit extends RealmObject {
    private static int id_inc = 0;

    @PrimaryKey
    private int id;
    public String title;
    public String description;
    public double price;
    public String image;

    public Produit(String _title, String _description, double _price,String _image) {
        this.title = _title;
        this.description = _description;
        this.price = _price;
        this.image = _image;
    }

    public Produit() {
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

    public static int getIdInc() {
        return ++id_inc;
    }
}
