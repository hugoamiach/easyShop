package com.example.easyshop;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Article extends RealmObject {
    private String nom;
    private double prix;
    private String description;
    private String url;

    public Article() {
        new Article("default", "default", 0, "");
    }

    public Article(String nom, String description, double prix, String url) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.url = url;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "Article num : ".concat(this.nom).concat(" ").concat(String.valueOf(this.prix)).concat("€ ").concat(this.description);
    }
}
