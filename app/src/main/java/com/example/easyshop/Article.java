package com.example.easyshop;

public class Article {
    String nom;
    double prix;
    String description;
    String url;

    public Article(String nom, double prix, String description, String url) {
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
}
