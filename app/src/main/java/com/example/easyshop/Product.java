package com.example.easyshop;

import com.example.easyshop.Entities.IEntities;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject implements IEntities {
    private static final List<String> categories;
    public static final String CHAUSSURE_CONST = "Chaussures";
    public static final String VETEMENT_CONST = "Vetements";

    public static List<String> getCategories() {
        return categories;
    }

    static {
        categories = new ArrayList<>();
        categories.add(CHAUSSURE_CONST);
        categories.add(VETEMENT_CONST);
    }

    private String title;
    private String image;
    private String description;
    private double price;
    private String typeProduct;

    @PrimaryKey
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public void setDescription(String desc) {
        this.description = desc;

    }

    public void setPrice(double prix) {
        this.price = prix;

    }

    public void setImage(String link_image) {
        this.image = link_image;
    }

    @Override
    public int getId() {
        return id;
    }
}