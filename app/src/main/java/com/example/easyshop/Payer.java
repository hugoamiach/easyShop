package com.example.easyshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Payer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final boolean[] code = {false};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payer);
        Bundle bundle = getIntent().getExtras();
        final String str = bundle.getString("panier");
        final TextView textView = (TextView) findViewById(R.id.panier);
        textView.setText("Montant du panier ".concat(str).concat("€"));

        final EditText codePromo = (EditText) findViewById(R.id.codePromo);
        Button b1 = (Button)findViewById(R.id.buttonCodePromo);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(codePromo.getText().toString().equals("HOUHOU2019") && code[0] == false){
                    Toast.makeText(getApplicationContext(), "Code Promo Valide",Toast.LENGTH_SHORT).show();
                    code[0] = true;
                    double value = Double.valueOf(str);
                    value = value * 0.9;
                    DecimalFormat df = new DecimalFormat("0.00");
                    textView.setText("Montant du panier ".concat(df.format(value)).concat("€"));
                }if(codePromo.getText().toString().equals("HOUHOU2019") && code[0] == true){
                    Toast.makeText(getApplicationContext(), "Code promo deja utilise",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Code promo errone",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
