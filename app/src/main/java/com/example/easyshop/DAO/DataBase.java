package com.example.easyshop.DAO;

import android.content.Context;

import com.example.easyshop.Product;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DataBase {
    private Realm realm;
    private static final DataBase ourInstance = new DataBase();
    private static final String baseName = "myrealm.realm";

    public static DataBase getInstance() {
        return ourInstance;
    }

    private DataBase() {
    }

    public Realm getRealm(Context context) {
        if (realm == null)
            init(context);
        return realm;
    }

    private void init(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name(baseName).initialData(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                List<Product> products = new ArrayList<>();
                products.add(new Product("Timberland", "Timberland marron", 99.99, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRKpOOAthYENJbddkT7DQMsaU3pcxqhPfWESjb2GdkMiDGl635iRoSlYS3VViC2ikz2rcjHIQK6ESeWZTqaNy-K_Ba2HUfk5B0unbr8KjCDHDqpWKZKA09wOZM&usqp=CAc"));
                products.add(new Product("Crocs", "Crocs on fire", 29.99, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcQdBkz7u0wCHbHNJaiABpSGHUWjVfwD3-ajkfwu8PFy2e6mNGE194QzZ0ArkT_r4umM3yS2eavj&usqp=CAc"));
                products.add(new Product("Nike", "Nike AF1", 74.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR7TP_JYFrILQeX7E1zQX86Khzimsn95BfF5bF-ubVmdMx-wZb5Q&s"));
                products.add(new Product("Adidas", "Yeezy Boost", 199.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3GjEeWZqsbJUrHjzfq87d1ethBooqQ1TIgj_aWzmex9EVa3GspA&s"));
                products.add(new Product("Adidas", "Maillot Juventus", 99.99, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSy_BbJNtcBkr7ejGa8_OUONGNFl8DHa-rWiMhV-hloZO6OP6bUNvln2iojGC9RGsiqfY_oVFhnvP6rbZwWs3S-d4XBZKikPnt4oTNBb-pHoFTXUkJw6knvSg&usqp=CAc"));
                products.add(new Product("Nasa", "Pull Nasa", 64.99, "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQk1D8qTVZOSICIFxqt3YWbUDDzQY_I_iqiguDIqFqFCc4gWvoLiwv70Zd6379A_FhoogEzO2uJ3Sf_h0EX11IyU-5WkbPM0Vw9Qvh74SQ&usqp=CAc"));
                products.add(new Product("Levis", "Jean Levis", 100, "https://www.street-online.com/Files/17303/Img/21/jean-levis-femme-721-taille-haute-skinny-to-the-nine-188820188-zoom-zoom.jpg"));
                products.add(new Product("Hollister", "T-shirt Hollister", 29.99, "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcR9ESNt81wDyTwPqNtLaJB1BAsSjlJePa3rRD28MzJadrdBr4QVWJxYahM_StfT9bGrcV9mjIX78TP8V6ctXcwz_nYAigB161vNHhMWO_zC1TDd9sfQ0DqJJw&usqp=CAc"));

                int cmp = 1;
                for (Product p : products) {
                    Product realmObject = realm.createObject(Product.class, cmp++);
                    realm.copyToRealm(realmObject);
                }
            }
        }).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }
}
