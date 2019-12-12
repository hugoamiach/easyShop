package com.example.easyshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.example.easyshop.DAO.ProductDAO;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivityAdmin extends AppCompatActivity {

    private List<Product> productiList;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        ProductDAO productDAO = new ProductDAO(this);
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
