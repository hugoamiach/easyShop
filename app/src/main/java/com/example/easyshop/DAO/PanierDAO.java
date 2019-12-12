package com.example.easyshop.DAO;

import android.content.Context;

import com.example.easyshop.Entities.Panier;

public class PanierDAO extends AbstractDAO<Panier> {
    public PanierDAO(Context context) {
        super(context, Panier.class);
    }
}
