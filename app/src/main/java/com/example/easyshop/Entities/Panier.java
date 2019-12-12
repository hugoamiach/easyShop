package com.example.easyshop.Entities;

import com.example.easyshop.DAO.PanierDAO;
import com.example.easyshop.Product;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Panier extends RealmObject implements IEntities {
    private RealmList<Product> products;

    public Panier() {
        this.products = new RealmList<>();
    }

    public RealmList<Product> getProducts() {
        return products;
    }

    public boolean addProduct(PanierDAO panierDAO, Product product) {
        getRealm().beginTransaction();
        boolean b = products.add(product);
        getRealm().commitTransaction();
        panierDAO.update(this);

        return b;
    }

    @PrimaryKey
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}