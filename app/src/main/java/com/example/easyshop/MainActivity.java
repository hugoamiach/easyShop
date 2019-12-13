package com.example.easyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.easyshop.DAO.PanierDAO;
import com.example.easyshop.DAO.ProductDAO;
import com.example.easyshop.Entities.Panier;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Product> productiListCh;
    private static final String CHANNEL_ID = "1111";
    private static final int NOTIFICATION_ID = 1;
    private ArrayList<Product> ProductiListCh;

    private ProductAdapter mProductAdapterCh;
    private List<Product> productiListVt;
    private ProductAdapter mProductAdapterVt;
    private Panier Panier;
    private ProductAdapter mProductAdapterPa;
    private MaterialButton mButton;
    private PanierDAO panierDAO;

    public void pay(View view) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Votre panier est prêt")
                .setContentText("N'oublier d'utiliser le code Promo HOUHOU2019")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // notificationId est un identificateur unique par notification qu'il vous faut définir
        notificationManager.notify(NOTIFICATION_ID, notifBuilder.build());
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
        for (Product product : Panier.getProducts()) {
            res += product.getPrice();
        }
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        panierDAO = new PanierDAO(this);
        this.Panier = panierDAO.getFindFirst();

        if (Panier == null) {
            Panier = panierDAO.create();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductDAO productDAO = new ProductDAO(this);

        //productDAO.delete(productCreate);

        List<Product> products = productDAO.getAll();

        productiListCh = new ArrayList<>();
        productiListVt = new ArrayList<>();
        for (Product e : products) {
            if (e.getTypeProduct() != null) {
                if (e.getTypeProduct().equals(Product.CHAUSSURE_CONST))
                    productiListCh.add(e);

                if (e.getTypeProduct().equals(Product.VETEMENT_CONST))
                    productiListVt.add(e);
            }
        }


        final TextView total = findViewById(R.id.total);
        calculerMontantPanier();
        total.setText("Montant du panier : " + this.calculerMontantPanier() + "€");

        TabHost tabs = findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("tag1");
        final ListView listViewCatalogCh = findViewById(R.id.listview);
        mProductAdapterCh = new ProductAdapter(productiListCh, getLayoutInflater());
        listViewCatalogCh.setAdapter(mProductAdapterCh); //Pose problème
        listViewCatalogCh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, productiListCh.get(i).getTitle() + " ajouté au panier", Toast.LENGTH_SHORT).show();
                mProductAdapterPa.notifyDataSetChanged();
                Panier.addProduct(panierDAO, productiListCh.get(i));
                calculerMontantPanier();
                total.setText("Montant du panier : " + calculerMontantPanier() + "€");
            }
        });
        spec.setContent(R.id.listview);
        spec.setIndicator("Chaussures");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag2");
        final ListView listViewCatalogVt = findViewById(R.id.listview1);
        mProductAdapterVt = new ProductAdapter(productiListVt, getLayoutInflater());
        listViewCatalogVt.setAdapter(mProductAdapterVt);
        listViewCatalogVt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, productiListVt.get(i).getTitle() + " ajouté au panier", Toast.LENGTH_SHORT).show();
                mProductAdapterPa.notifyDataSetChanged();
                Panier.addProduct(panierDAO, productiListVt.get(i));
                calculerMontantPanier();
                total.setText("Montant du panier : " + calculerMontantPanier() + "€");
            }
        });
        spec.setContent(R.id.listview1);
        spec.setIndicator("Vetements");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag3");
        final ListView listViewCatalogPa = findViewById(R.id.listview2);
        mProductAdapterPa = new ProductAdapter(Panier.getProducts(), getLayoutInflater());
        listViewCatalogPa.setAdapter(mProductAdapterPa);

        listViewCatalogPa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, Panier.getProducts().get(i).getTitle() + " Suppression du panier", Toast.LENGTH_SHORT).show();
                mProductAdapterPa.notifyDataSetChanged();
                Panier.getRealm().beginTransaction();
                Panier.getProducts().remove(i);
                Panier.getRealm().commitTransaction();
                calculerMontantPanier();
                total.setText("Montant du panier : " + calculerMontantPanier() + "€");
            }


        });

        mButton = findViewById(R.id.vider);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Le panier est vide", Toast.LENGTH_SHORT).show();
                Panier.getRealm().beginTransaction();
                Panier.getProducts().clear();
                Panier.getRealm().commitTransaction();
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

