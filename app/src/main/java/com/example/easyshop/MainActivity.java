package com.example.easyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> ProductiListCh;
    private ProductAdapter mProductAdapterCh;
    private ArrayList<Product> ProductiListVt;
    private ProductAdapter mProductAdapterVt;
    private ArrayList<Product> Panier;
    private ProductAdapter mProductAdapterPa;
    private Button mButton;
    private Realm realm;

    public void pay(View view) {
        Intent intent = new Intent(this, Pay.class);
        intent.putExtra("panier", calculerMontantPanier());
        startActivity(intent);
    }

    public String calculerMontantPanier() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(calculPanier());
    }

    public double calculPanier() {
        double res = 0;
        for (int i = 0; i < Panier.size(); i++) {
            res += Panier.get(i).price;
        }
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Realm.init(this);

        Product p1 = new Product(1, "Timberland", "Timberland marron", 99.99, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRKpOOAthYENJbddkT7DQMsaU3pcxqhPfWESjb2GdkMiDGl635iRoSlYS3VViC2ikz2rcjHIQK6ESeWZTqaNy-K_Ba2HUfk5B0unbr8KjCDHDqpWKZKA09wOZM&usqp=CAc");
        Product p2 = new Product(2, "Crocs", "Crocs on fire", 29.99, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcQdBkz7u0wCHbHNJaiABpSGHUWjVfwD3-ajkfwu8PFy2e6mNGE194QzZ0ArkT_r4umM3yS2eavj&usqp=CAc");
        Product p3 = new Product(3, "Nike", "Nike AF1", 74.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR7TP_JYFrILQeX7E1zQX86Khzimsn95BfF5bF-ubVmdMx-wZb5Q&s");
        Product p4 = new Product(4, "Adidas", "Yeezy Boost", 199.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3GjEeWZqsbJUrHjzfq87d1ethBooqQ1TIgj_aWzmex9EVa3GspA&s");
        Product p5 = new Product(4, "Adidas", "Maillot Juventus", 99.99, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSy_BbJNtcBkr7ejGa8_OUONGNFl8DHa-rWiMhV-hloZO6OP6bUNvln2iojGC9RGsiqfY_oVFhnvP6rbZwWs3S-d4XBZKikPnt4oTNBb-pHoFTXUkJw6knvSg&usqp=CAc");
        Product p6 = new Product(3, "Nasa", "Pull Nasa", 64.99, "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQk1D8qTVZOSICIFxqt3YWbUDDzQY_I_iqiguDIqFqFCc4gWvoLiwv70Zd6379A_FhoogEzO2uJ3Sf_h0EX11IyU-5WkbPM0Vw9Qvh74SQ&usqp=CAc");
        Product p7 = new Product(3, "Levis", "Jean Levis", 100, "https://www.street-online.com/Files/17303/Img/21/jean-levis-femme-721-taille-haute-skinny-to-the-nine-188820188-zoom-zoom.jpg");
        Product p8 = new Product(3, "Hollister", "T-shirt Hollister", 29.99, "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcR9ESNt81wDyTwPqNtLaJB1BAsSjlJePa3rRD28MzJadrdBr4QVWJxYahM_StfT9bGrcV9mjIX78TP8V6ctXcwz_nYAigB161vNHhMWO_zC1TDd9sfQ0DqJJw&usqp=CAc");

        //Product p9 = new Product(3, "Nike", "Nike AF1", 74.99);
        //init();
        Panier = new ArrayList<>();
        ProductiListCh = new ArrayList<>();
        ProductiListVt = new ArrayList<>();

        ProductiListCh.add(p1);
        ProductiListCh.add(p2);
        ProductiListCh.add(p3);
        ProductiListCh.add(p4);

        ProductiListVt.add(p5);
        ProductiListVt.add(p6);
        ProductiListVt.add(p7);
        ProductiListVt.add(p8);
        //ProductiListVt.add(p9);


        final TextView total = findViewById(R.id.total);
        calculerMontantPanier();
        total.setText("Montant du panier : " + this.calculerMontantPanier() + "€");

        TabHost tabs = findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("tag1");
        final ListView listViewCatalogCh = findViewById(R.id.listview);
        mProductAdapterCh = new ProductAdapter(ProductiListCh, getLayoutInflater());
        listViewCatalogCh.setAdapter(mProductAdapterCh); //Pose problème
        listViewCatalogCh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ProductiListCh.get(i).title + " ajouté au panier", Toast.LENGTH_SHORT).show();
                mProductAdapterPa.notifyDataSetChanged();
                Panier.add(ProductiListCh.get(i));
                calculerMontantPanier();
                total.setText("Montant du panier : " + calculerMontantPanier() + "€");
            }
        });
        spec.setContent(R.id.listview);
        spec.setIndicator("Chaussures");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag2");
        final ListView listViewCatalogVt = findViewById(R.id.listview1);
        mProductAdapterVt = new ProductAdapter(ProductiListVt, getLayoutInflater());
        listViewCatalogVt.setAdapter(mProductAdapterVt);
        listViewCatalogVt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ProductiListVt.get(i).title + " ajouté au panier", Toast.LENGTH_SHORT).show();
                mProductAdapterPa.notifyDataSetChanged();
                Panier.add(ProductiListVt.get(i));
                calculerMontantPanier();
                total.setText("Montant du panier : " + calculerMontantPanier() + "€");
            }
        });
        spec.setContent(R.id.listview1);
        spec.setIndicator("Vetements");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag3");
        final ListView listViewCatalogPa = findViewById(R.id.listview2);
        mProductAdapterPa = new ProductAdapter(Panier, getLayoutInflater());
        listViewCatalogPa.setAdapter(mProductAdapterPa);
        listViewCatalogPa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, Panier.get(i).title + " Suppression du panier", Toast.LENGTH_SHORT).show();
                mProductAdapterPa.notifyDataSetChanged();
                Panier.remove(i);
                calculerMontantPanier();
                total.setText("Montant du panier : " + calculerMontantPanier() + "€");
            }


        });

        mButton = findViewById(R.id.vider);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Le panier est vide", Toast.LENGTH_SHORT).show();
                Panier.clear();
                mProductAdapterPa.notifyDataSetChanged();
                calculerMontantPanier();
                total.setText("Montant du panier : " + calculerMontantPanier() + "€");
            }
        });

        spec.setContent(R.id.listview2);
        spec.setIndicator("Panier");
        tabs.addTab(spec);
    }
}

