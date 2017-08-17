package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.app_proyecto_i_aplicada.R;
import domain.Product;

/**
 * Created by Machito on 16/8/2017.
 */

public class ProductAdapter extends BaseAdapter{

    protected Activity activity;

    protected ArrayList<Product> productsItems;

    public ProductAdapter(Activity activity, ArrayList<Product> productsItems) {
        this.activity = activity;
        this.productsItems = productsItems;
    }

    @Override
    public int getCount() {
        return productsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return productsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productsItems.get(position).getIdProduct();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(convertView== null){
            LayoutInflater inflater= (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_product_list, null);
        }


        Product product = productsItems.get(position);

        //Se establecen los valores que llevaran cada componente en el listview segun la posicion que tengas en el array list
        ImageView ivProduct=(ImageView) view.findViewById(R.id.ivProduct);
        TextView tvProductName=(TextView) view.findViewById(R.id.tvProductName);
        TextView tvProductPrice=(TextView) view.findViewById(R.id.tvProductPrice);

        ivProduct.setImageDrawable(product.getImageProduct());
        tvProductName.setText(product.getName());

        ///Se debe de llamar a la funcion para convertir el precio a dolar
        tvProductPrice.setText(tvProductPrice.getText()+ "CRC "+product.getPrice()
                                +"\n\t\t\t\t\t\t\tUSD "+product.getPrice());

        //retorna un activity con los items que contendra el listview
        return view;
    }
}
