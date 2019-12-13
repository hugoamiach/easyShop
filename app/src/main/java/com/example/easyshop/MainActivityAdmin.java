package com.example.easyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easyshop.DAO.ProductDAO;

import java.util.List;

public class MainActivityAdmin extends AppCompatActivity {

    private List<Product> productiList;
    private ProductAdapter mProductAdapter;


    public void Validate(View view) {
        final ProductDAO productDAO = new ProductDAO(this);
        final EditText nomProd = findViewById(R.id.nomProd);
        final EditText descProd = findViewById(R.id.descProd);
        final EditText prix = findViewById(R.id.prix);
        final EditText url = findViewById(R.id.url);
        final Spinner Cat = findViewById(R.id.Categories);

        Product productCreate = productDAO.create();
        productDAO.getRealm().beginTransaction();
        productCreate.setImage(url.getText().toString());
        productCreate.setDescription(descProd.getText().toString());
        productCreate.setTitle(nomProd.getText().toString());
        double value = Double.parseDouble(prix.getText().toString());
        productCreate.setPrice(value);
        productCreate.setTypeProduct(Cat.getSelectedItem().toString());
        productDAO.getRealm().commitTransaction();
        productDAO.update(productCreate);

        Intent Product = new Intent(this, ProductAdded.class);
        startActivity(Product);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        final ProductDAO productDAO = new ProductDAO(this);
        productiList = productDAO.getAll();

        TabHost tabs = findViewById(R.id.tabhost);
        tabs.setup();
        TabSpec spec = tabs.newTabSpec("tag1");
        final ListView listViewCatalog = findViewById(R.id.listviewAdmin);
        mProductAdapter = new ProductAdapter(productiList, getLayoutInflater());
        listViewCatalog.setAdapter(mProductAdapter); //Pose problème
        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivityAdmin.this, productiList.get(i).getTitle() + "Supression de la base", Toast.LENGTH_SHORT).show();
                productDAO.delete(productiList.get(i));
                mProductAdapter.notifyDataSetChanged();
            }
        });
        spec.setContent(R.id.listviewAdmin);
        spec.setIndicator("Articles");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        //final EditText EditText = findViewById(R.id.nomProd);
        spec.setContent(R.id.editText);
        //spec.setContent(R.id.nomProd);
        //spec.setContent(R.id.descProd);
        //spec.setContent(R.id.prix);
        //spec.setContent(R.id.url);
        spec.setIndicator("Ajout Articles");
        tabs.addTab(spec);

    }
}
