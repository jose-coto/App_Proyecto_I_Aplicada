package cr.ac.ucr.app_proyecto_i_aplicada;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import adapter.ProductAdapter;
import domain.Product;


public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        final ListView lvProducts = (ListView) findViewById(R.id.lvProductList);
        final ArrayList<Product> productsList = new ArrayList<Product>();

        Product prod;

        for (int i =0; i<20;i++) {
            prod = new Product(i, "Hola", "Hola minfo", 123, getResources().getDrawable(R.drawable.youtube_logo));

            productsList.add(prod);
        }

        //Se crea el adaptador para que contiene los item con los productos
        ProductAdapter adapter = new ProductAdapter(this,productsList);

        //Se le añade el evento sobre el item
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Se crea un nuevo activity que desplegara la info del producto seleccionado
                Intent intent= new Intent(ProductListActivity.this, ProductDetailsActivity.class);

                //Se pasa el objeto seleccionado para poder utilizarlo en el activity de detalle
                Product productSelected= (Product) lvProducts.getItemAtPosition(i);
                intent.putExtra("Product", productSelected.getIdProduct());
                startActivity(intent);
            }
        });

        //se establece el adapter al listview
        lvProducts.setAdapter(adapter);
    }
}
