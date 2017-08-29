package cr.ac.ucr.app_proyecto_i_aplicada;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextClock;
import android.widget.TextView;

import adapter.ProductAdapter;
import domain.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView tvProductName;
    private TextView tvProductCRCValue;
    private TextView tvProductUSDValue;
    private Product object;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detalles");

        this.object= getProductForPutExtra();

        tvProductName=(TextView)findViewById(R.id.tvProductNameDetail);
        tvProductCRCValue=(TextView)findViewById(R.id.tvValuePriceProductDetail);
        tvProductUSDValue=(TextView)findViewById(R.id.tvValueDollarPriceProductDetail);

        tvProductName.setText(object.getName());
        tvProductCRCValue.setText("¢: "+object.getPrice());
        tvProductUSDValue.setText("$: "+object.getDollarPrice());


    }

    private Product getProductForPutExtra(){
        Product objet = new Product();
        objet.setIdProduct(getIntent().getExtras().getInt("id"));
        objet.setName(getIntent().getExtras().getString("name"));
        objet.setDescription(getIntent().getExtras().getString("description"));
        objet.setPrice(getIntent().getExtras().getFloat("price"));
        objet.setDollarPrice(getIntent().getExtras().getFloat("usdPrice"));

        return objet;
    }
}
