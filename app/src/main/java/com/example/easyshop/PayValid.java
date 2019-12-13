package com.example.easyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easyshop.DAO.PanierDAO;

public class PayValid  extends AppCompatActivity {

    private com.example.easyshop.Entities.Panier Panier;
    private PanierDAO panierDAO;

    public void Accueil(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_valid);

        panierDAO = new PanierDAO(this);
        this.Panier = panierDAO.getFindFirst();

        if (Panier == null) {
            Panier = panierDAO.create();
        }

        Panier.getRealm().beginTransaction();
        Panier.getProducts().clear();
        Panier.getRealm().commitTransaction();

    }
}
