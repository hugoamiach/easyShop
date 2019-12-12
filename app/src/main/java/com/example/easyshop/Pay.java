package com.example.easyshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean[] code = {false};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String str = bundle.getString("panier");
        final TextView textView = findViewById(R.id.panier);
        assert str != null;
        textView.setText("Montant du panier ".concat(str).concat("€"));
        final EditText codePromo = findViewById(R.id.codePromo);
        Button b1 = findViewById(R.id.buttonCodePromo);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codePromo.getText().toString().equals("HOUHOU2019") && code[0]) {
                    Toast.makeText(getApplicationContext(), "Code promo deja utilise", Toast.LENGTH_SHORT).show();
                }if (codePromo.getText().toString().equals("HOUHOU2019") && !code[0]) {
                    Toast.makeText(getApplicationContext(), "Code Promo Valide", Toast.LENGTH_SHORT).show();
                    code[0] = true;
                    double value = Double.parseDouble(str);
                    value = value * 0.9;
                    DecimalFormat df = new DecimalFormat("0.00");
                    textView.setText("Montant du panier ".concat(df.format(value)).concat("€"));
                }else {
                    Toast.makeText(getApplicationContext(), "Code promo errone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
