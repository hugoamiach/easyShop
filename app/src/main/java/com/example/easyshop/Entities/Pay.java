package com.example.easyshop.Entities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easyshop.R;

public class Pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payer);
        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("panier");
        TextView textView = (TextView) findViewById(R.id.panier);
        textView.setText("Montant du panier ".concat(str).concat("€"));
    }
}
