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

import com.example.easyshop.DAO.ProductDAO;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity {
    private List<Product> productiListCh;
    private static final String CHANNEL_ID = "1111";
    private static final int NOTIFICATION_ID = 1;
    private ArrayList<Product> ProductiListCh;

    private ProductAdapter mProductAdapterCh;
    private List<Product> productiListVt;
    private ProductAdapter mProductAdapterVt;
    private List<Product> Panier = new ArrayList<>();
    private ProductAdapter mProductAdapterPa;
    private Button mButton;

    public void pay(View view) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Votre panier est prêt")
                .setContentText("Voici le montant qu'il vous reste à regler ".concat(calculerMontantPanier().concat("€")))
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
        for (int i = 0; i < Panier.size(); i++) {
            res += Panier.get(i).getPrice();
        }
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductDAO productDAO = new ProductDAO(this);
        Product productCreate = productDAO.create();

        productDAO.getRealm().beginTransaction();
        productCreate.setImage("https://fashion-day.fr/3444-large/manteau-hiver-pas-cher-femme-noir-et-fourrure-noire.jpg");
        productCreate.setDescription("Manteau femme");
        productCreate.setTitle("Manteau");
        productCreate.setTypeProduct(Product.VETEMENT_CONST);
        productDAO.getRealm().commitTransaction();

        productDAO.update(productCreate);


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
                Panier.add(productiListCh.get(i));
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
                Panier.add(productiListVt.get(i));
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
                Toast.makeText(MainActivity.this, Panier.get(i).getTitle() + " Suppression du panier", Toast.LENGTH_SHORT).show();
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

