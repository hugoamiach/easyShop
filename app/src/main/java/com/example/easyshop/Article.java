package com.example.easyshop;

import com.example.easyshop.Entities.AbstractEntities;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Article extends AbstractEntities {
    private static int id_inc = 0;

    String nom;
    double prix;
    String description;
    String url;

    public Article(String nom, String description, double prix, String url) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.url = url;
    }

    public Article() {

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
        return "Article num : " + this.getId() + " " + this.nom + " " + this.prix + "€ " + this.description;
    }
}
