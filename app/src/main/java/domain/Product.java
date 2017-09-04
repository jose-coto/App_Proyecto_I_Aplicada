package domain;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Machito on 16/8/2017.
 */

public class Product {

    private int idProduct;
    private String name;
    private String description;
    private float price;
    private float dollarPrice;
    private Drawable imageProduct;
    private String category;

    public Product(){

    }
    public Product(int idProduct, String name, String description, float price,float dollarPrice, Drawable imageProduct,String category) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dollarPrice=dollarPrice;
        this.imageProduct = imageProduct;
        this.category=category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(float dollarPrice) {
        this.dollarPrice = dollarPrice;
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