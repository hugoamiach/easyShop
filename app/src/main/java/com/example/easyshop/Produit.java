package com.example.easyshop;

import com.example.easyshop.Entities.AbstractEntities;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Produit extends AbstractEntities implements RealmModel {
    public String title;
    public String description;
    public double price;
    public String image;

    public Produit(String _title, String _description, double _price, String _image) {
        this.title = _title;
        this.description = _description;
        this.price = _price;
        this.image = _image;
    }

    public Produit() {
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
