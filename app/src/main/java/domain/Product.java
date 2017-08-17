package domain;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Machito on 16/8/2017.
 */

public class Product{

    private int idProduct;
    private String name;
    private String description;
    private float price;
    private Drawable imageProduct;

    public Product(int idProduct, String name, String description, float price, Drawable imageProduct) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageProduct = imageProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Drawable getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(Drawable imageProduct) {
        this.imageProduct = imageProduct;
    }
}