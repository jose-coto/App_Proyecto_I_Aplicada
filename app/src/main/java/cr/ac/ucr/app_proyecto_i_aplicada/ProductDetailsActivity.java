package cr.ac.ucr.app_proyecto_i_aplicada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import adapter.ProductAdapter;
import domain.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        text=(TextView)findViewById(R.id.textdddView);

        Bundle extras= getIntent().getExtras();

    }
}
