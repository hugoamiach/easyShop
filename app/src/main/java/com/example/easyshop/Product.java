package com.example.easyshop;

<<<<<<< HEAD:app/src/main/java/com/example/easyshop/Product.java
public class Product {
=======
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
>>>>>>> 59287b799aa82bd5b85168a1787ad91fd5be81f1:app/src/main/java/com/example/easyshop/Produit.java

public class Produit extends RealmObject {
    private static int id_inc = 0;

    @PrimaryKey
    private int id;
    public String title;
    public String description;
    public double price;
    public String image;

<<<<<<< HEAD:app/src/main/java/com/example/easyshop/Product.java
    public Product(int id, String title, String description, double price) {
        new Product(id, title, description, price, "");
=======
    public Produit(String _title, String _description, double _price,String _image) {
        this.title = _title;
        this.description = _description;
        this.price = _price;
        this.image = _image;
>>>>>>> 59287b799aa82bd5b85168a1787ad91fd5be81f1:app/src/main/java/com/example/easyshop/Produit.java
    }

    public Product(int id, String title, String description, double price, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return description;
    }

    public String getimage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductImage(String image) {
        this.image = image;
    }
<<<<<<< HEAD:app/src/main/java/com/example/easyshop/Product.java
=======

    public static int getIdInc() {
        return ++id_inc;
    }
>>>>>>> 59287b799aa82bd5b85168a1787ad91fd5be81f1:app/src/main/java/com/example/easyshop/Produit.java
}
