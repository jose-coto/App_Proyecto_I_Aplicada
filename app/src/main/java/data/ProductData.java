package data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import connection.BCCRConection;
import cr.ac.ucr.app_proyecto_i_aplicada.R;
import domain.Product;

/**
 * Created by Machito on 3/9/2017.
 */

public class ProductData {

    private Context context;
    private Float valueDollar;

    public ProductData(Context context) {
        this.context = context;
    }

    public ArrayList<Product> getAllProducts(){

        //Llamo al web service del BCCR
        valueDollar= getValueDollarBCCR();

        ArrayList<Product> productsList = new ArrayList<Product>();

        Product prod = new Product();
        prod.setIdProduct(0);
        prod.setName("iPhone 7");
        prod.setDescription("Celular iPhone 7");
        prod.setPrice(405600);
        prod.setDollarPrice(getDollarValue(prod.getPrice(),valueDollar));
        prod.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod.setCategory("Tecnología");
        productsList.add(prod);

        Product prod1 = new Product();
        prod1.setIdProduct(1);
        prod1.setName("Lavadora Atlas");
        prod1.setDescription("Capacidad 20 kg");
        prod1.setPrice(500000);
        prod1.setDollarPrice(getDollarValue(prod1.getPrice(),valueDollar));
        prod1.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod1.setCategory("Linea Blanca");
        productsList.add(prod1);

        Product prod2 = new Product();
        prod2.setIdProduct(2);
        prod2.setName("Xperia X");
        prod2.setDescription("Sony Xperia X con 20 megapixeles de camara");
        prod2.setPrice(300000);
        prod2.setDollarPrice(getDollarValue(prod2.getPrice(),valueDollar));
        prod2.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod2.setCategory("Tecnología");
        productsList.add(prod2);

        Product prod3 = new Product();
        prod3.setIdProduct(3);
        prod3.setName("Kylie Jenner Labial");
        prod3.setDescription("Labial rojo");
        prod3.setPrice(5000);
        prod3.setDollarPrice(getDollarValue(prod3.getPrice(),valueDollar));
        prod3.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod3.setCategory("Accesorios");
        productsList.add(prod3);

        Product prod4 = new Product();
        prod4.setIdProduct(4);
        prod4.setName("Sudadera Nike Combat");
        prod4.setDescription("DryFit Tecnology");
        prod4.setPrice(30000);
        prod4.setDollarPrice(getDollarValue(prod4.getPrice(),valueDollar));
        prod4.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod4.setCategory("Ropa");
        productsList.add(prod4);

        Product prod5 = new Product();
        prod5.setIdProduct(15);
        prod5.setName("Cien años de Soledad");
        prod5.setDescription("Gabriel García Marquez");
        prod5.setPrice(15000);
        prod5.setDollarPrice(getDollarValue(prod5.getPrice(),valueDollar));
        prod5.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod5.setCategory("Libros");
        productsList.add(prod5);

        Product prod6 = new Product();
        prod6.setIdProduct(6);
        prod6.setName("Peine alizador");
        prod6.setDescription("Tecnologia de punta para aplanchar tu cabello");
        prod6.setPrice(575);
        prod6.setDollarPrice(getDollarValue(prod6.getPrice(),valueDollar));
        prod6.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod6.setCategory("Accesorios");
        productsList.add(prod6);

        Product prod7 = new Product();
        prod7.setIdProduct(1);
        prod7.setName("Cocina Electrica Mabe");
        prod7.setDescription("Cocina con disco en ceramica");
        prod7.setPrice(250000);
        prod7.setDollarPrice(getDollarValue(prod7.getPrice(),valueDollar));
        prod7.setImageProduct(context.getResources().getDrawable(R.drawable.core_vises_logo));
        prod7.setCategory("Linea Blanca");
        productsList.add(prod7);

        return productsList;
    }


    public ArrayList<Product> getProductsByCategory(String nameCategory){
        ArrayList<Product> productsListFiltered = new ArrayList<Product>();
        ArrayList<Product> productsList = getAllProducts();

        for (int i=0;i<productsList.size();i++) {
            Product product= productsList.get(i);
            if(product.getCategory().equalsIgnoreCase(nameCategory)){
                productsListFiltered.add(product);
            }
        }
        return productsListFiltered;
    }

    private Float getValueDollarBCCR(){
        Float value=null;
        BCCRConection bccr=new BCCRConection();
        try {
            value= bccr.execute().get();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

        return value;
    }
    private Float getDollarValue(Float price,Float usdValue){
        return price/usdValue;
    }

    public ArrayList<Product> getProductByName(String valueSearch) {
        ArrayList<Product> productsListFiltered = new ArrayList<Product>();
        ArrayList<Product> productsList = getAllProducts();

        for (int i=0;i<productsList.size();i++) {
            Product product= productsList.get(i);
            if(product.getName().contains(valueSearch)){
                productsListFiltered.add(product);
            }
        }
        return productsListFiltered;
    }

    private Bitmap getImageByUrl(String urlImage){

        URL imageUrl = null;
        HttpURLConnection conn = null;
        Bitmap imagen=null;
        try {

            imageUrl = new URL("http://pagina.com/foto.jpg");
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2

            imagen = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);

        } catch (IOException e) {

            e.printStackTrace();

        }
        return imagen;
    }
}
