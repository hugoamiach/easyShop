package com.example.easyshop.DAO;

import android.content.Context;

import com.example.easyshop.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    public ProductDAO(Context context) {
        super(context, Product.class);
    }
    
}