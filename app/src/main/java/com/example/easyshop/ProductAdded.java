package com.example.easyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProductAdded extends AppCompatActivity {

    public void Accueil(View view) {
        Intent mainActivityAdmin = new Intent(this, MainActivityAdmin.class);
        startActivity(mainActivityAdmin);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_added);
    }
}
