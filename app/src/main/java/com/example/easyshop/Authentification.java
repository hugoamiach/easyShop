package com.example.easyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Authentification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification);

        final EditText username = findViewById(R.id.login);
        final EditText password = findViewById(R.id.password);
        Button b1 = findViewById(R.id.buttonlogin);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equalsIgnoreCase("admin")) {
                    Intent mainActivityAdmin = new Intent(Authentification.this, MainActivityAdmin.class);
                    startActivity(mainActivityAdmin);
                } else if (username.getText().toString().equals(password.getText().toString())) {
                    Intent mainActivity = new Intent(Authentification.this, MainActivity.class);
                    startActivity(mainActivity);
                } else {
                    Toast.makeText(Authentification.this, "Erreur d'itentification !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}