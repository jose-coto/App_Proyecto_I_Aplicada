package cr.ac.ucr.app_proyecto_i_aplicada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.SearchView;

import java.io.Serializable;
import java.util.ArrayList;


import adapter.ProductAdapter;
import domain.Product;


public class ProductListActivity extends AppCompatActivity {

    private ListView lvProducts;
    private Toolbar toolbar;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        lvProducts = (ListView) findViewById(R.id.lvProductList);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Productos");

        ArrayList<Product> productsList = new ArrayList<Product>();

        Product prod = new Product(1, "Hola", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod);

        Product prod1 = new Product(1, "Luis", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod1);

        Product prod2 = new Product(1, "Ho", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod2);

        adapter = new ProductAdapter(this,productsList);

        lvProducts.setAdapter(adapter);

        //Se le añade el evento sobre el item
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int y= position;
                //Se crea un nuevo activity que desplegara la info del producto seleccionado
                Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
                Product map = (Product) parent.getItemAtPosition(position);

                intent.putExtra("id", map.getIdProduct());
                intent.putExtra("name", map.getName());
                intent.putExtra("price", map.getPrice());
                intent.putExtra("usdPrice", map.getDollarPrice());
                intent.putExtra("description", map.getDescription());
                //intent.putExtra("link", (Serializable) map.getImageProduct());

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);

        MenuItem item = menu.findItem(R.id.menusearch);

        SearchView searchView =(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
