package com.example.easyshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityAdmin extends AppCompatActivity {

    private ArrayList<Product> ProductiList;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
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
        ProductiList = new ArrayList<>();
        ProductiList.add(p1);
        ProductiList.add(p2);
        ProductiList.add(p3);
        ProductiList.add(p4);
        ProductiList.add(p5);
        ProductiList.add(p6);
        ProductiList.add(p7);
        ProductiList.add(p8);
        //ProductiListVt.add(p9);

        TabHost tabs = findViewById(R.id.tabhost);
        tabs.setup();
        TabSpec spec = tabs.newTabSpec("tag1");
        final ListView listViewCatalog = findViewById(R.id.listviewAdmin);
        mProductAdapter = new ProductAdapter(ProductiList, getLayoutInflater());
        listViewCatalog.setAdapter(mProductAdapter); //Pose problème
        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivityAdmin.this, ProductiList.get(i).title + "Supression de la base",  Toast.LENGTH_SHORT).show();
                ProductiList.remove(i);
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
